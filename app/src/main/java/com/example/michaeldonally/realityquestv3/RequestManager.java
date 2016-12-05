package com.example.michaeldonally.realityquestv3;

import java.util.ArrayList;

import android.content.Context;

import com.android.volley.*;
import com.android.volley.toolbox.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* Sources:
 * https://developer.android.com/training/volley/requestqueue.html
 * http://stackoverflow.com/questions/28172496/android-volley-how-to-isolate-requests-in-another-class
 */

class RequestManager {
    private static RequestManager mInstance;

    private RequestQueue mQueue;
    private String mBaseUrl;

    private RequestManager(Context context) {
        mQueue = Volley.newRequestQueue(context);

        // TODO Probably shouldn't be hosting the server from my laptop...
        mBaseUrl = "http://70.29.104.101:1234";
    }

    static synchronized RequestManager getInstance() {
        if (mInstance == null) {
            throw new IllegalStateException("RequestManager not initialized, call RequestManager#setContext(Context) first.");
        }

        return mInstance;
    }

    /**
     * Call this once, from the main activity, to create the RequestManager object.
     */
    static synchronized void setContext(Context context) {
        mInstance = new RequestManager(context);
    }

    private void sendRequest(int method, String url, JSONObject parameters, final Response.Listener<JSONObject> responseListener) {
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        };

        System.out.println("Adding request to the queue.");
        JsonObjectRequest request = new JsonObjectRequest(method, url, parameters, responseListener, errorListener);
        mQueue.add(request);
    }

    void getUserDetails(String username, String password, final ResponseListener<User> listener) {
        int method = Request.Method.GET;
        String url = mBaseUrl + "/user/details?username=" + username + "&password=" + password;
        JSONObject parameters = null;

        Response.Listener<JSONObject> responseListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("Response get!");

                User user = new User();

                try {
                    user.username = response.getString("username");
                    user.password = response.getString("password");

                    user.character = new PlayerCharacter();
                    user.character.characterName = response.getString("name");
                    user.character.playerBio = response.getString("bio");
                    user.character.level = response.getInt("level");
                    user.character.healthPoints = response.getInt("hitPoints");
                } catch (JSONException|NullPointerException e) {
                    e.printStackTrace();
                    listener.getResult(null);
                }

                listener.getResult(user);
            }
        };

        sendRequest(method, url, parameters, responseListener);
    }

    void updateUserDetails(User user) {
        int method = Request.Method.POST;
        String url = mBaseUrl + "/user/upload";
        JSONObject parameters = new JSONObject();

        try {
            parameters.put("username", user.username);
            parameters.put("password", user.password);
            parameters.put("name", user.character.characterName);
            parameters.put("bio", user.character.playerBio);
            parameters.put("level", user.character.level);
            parameters.put("hitpoints", user.character.healthPoints);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        sendRequest(method, url, parameters, null);
    }

    void uploadEvent(Event event) {
        int method = Request.Method.POST;
        String url = mBaseUrl + "/creator/upload-event";
        JSONObject parameters = new JSONObject();

        try {
            parameters.put("title", event.name);
            parameters.put("imageId", 0);

            JSONArray options = new JSONArray();
            for (int i = 0; i < event.options.length; i++) {
                JSONObject option = new JSONObject();
                option.put("text", event.options[i]);
                option.put("reward", event.options[i]);
                option.put("punishment", event.options[i]);
                option.put("chance", event.options[i]);
                options.put(option);
            }
            parameters.put("options", options);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        sendRequest(method, url, parameters, null);
    }

    void uploadMap(Map map) {
        int method = Request.Method.POST;
        String url = mBaseUrl + "/creator/upload-map";
        JSONObject parameters = new JSONObject();

        try {
            parameters.put("title", map.title);
            parameters.put("creator", map.creator);
            parameters.put("rating", map.rating);
            parameters.put("plays", map.plays);

            JSONArray markers = new JSONArray();
            for (int i = 0; i < map.markers.size(); i++) {
                JSONObject marker = new JSONObject();
                marker.put("name", map.markers.get(i).name);
                marker.put("longitude", map.markers.get(i).markerLoc.longitude);
                marker.put("latitude", map.markers.get(i).markerLoc.latitude);
                markers.put(marker);
            }
            parameters.put("markers", markers);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        sendRequest(method, url, parameters, null);
    }

    void getMapList(final ResponseListener<ArrayList<String>> listener) {
        int method = Request.Method.GET;
        String url = mBaseUrl + "/player/get-map-list";
        JSONObject parameters = null;

        Response.Listener<JSONObject> responseListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                ArrayList<String> mapTitles = new ArrayList<>();

                try {
                    JSONArray responseArray = response.getJSONArray("strings");

                    for (int i = 0; i < responseArray.length(); i++) {
                        mapTitles.add(responseArray.getJSONObject(i).getString("title"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    listener.getResult(null);
                }

                listener.getResult(mapTitles);
            }
        };

        sendRequest(method, url, parameters, responseListener);
    }

    void getMapDetails(String mapName, final ResponseListener<Map> listener) {
        int method = Request.Method.GET;
        String url = mBaseUrl + "/player/get-map?mapname=" + mapName;
        JSONObject parameters = null;

        Response.Listener<JSONObject> responseListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Map map = new Map();

                try {
                    map.title = response.getString("title");
                    map.rating = response.getDouble("rating");
                    map.plays = response.getInt("plays");

                    JSONObject creatorObj = response.getJSONObject("creator");
                    map.creator = creatorObj.getString("username");

                    JSONArray markers = response.getJSONArray("markers");
                    for (int i = 0; i < markers.length(); i++) {
                        JSONObject marker = markers.getJSONObject(i);
                        map.markers.set(i, new Marker());
                        map.markers.get(i).name = marker.getString("name");
                        map.markers.get(i).markerLoc = new Coor();
                        map.markers.get(i).markerLoc.latitude = marker.getDouble("latitude");
                        map.markers.get(i).markerLoc.longitude = marker.getDouble("longitude");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    listener.getResult(null);
                }

                listener.getResult(map);
            }
        };

        sendRequest(method, url, parameters, responseListener);
    }

    void getRandomEvent(final ResponseListener<Event> listener) {
        int method = Request.Method.GET;
        String url = mBaseUrl + "/player/get-event";
        JSONObject parameters = null;

        Response.Listener<JSONObject> responseListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Event event = new Event();

                try {
                    event.name = response.getString("title");

                    // ALSO LOAD THE EVENT IMAGE HERE
                    // lol nvm there are no images

                    JSONArray options = response.getJSONArray("options");
                    for (int i = 0; i < 4; i++) {
                        event.options[i] = options.getJSONObject(i).getString("text");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    listener.getResult(null);
                }

                listener.getResult(event);
            }
        };

        sendRequest(method, url, parameters, responseListener);
    }

    void rateMap(String mapName, int rating) {
        int method = Request.Method.POST;
        String url = mBaseUrl + "/player/rate-map";
        JSONObject parameters = new JSONObject();

        try {
            parameters.put("mapname", mapName);
            parameters.put("rating", rating);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        sendRequest(method, url, parameters, null);
    }
}
