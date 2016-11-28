package test.request;

import java.util.ArrayList;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.TimeUnit;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;

/* Sources:
 * https://developer.android.com/training/volley/requestqueue.html
 * http://stackoverflow.com/questions/28172496/android-volley-how-to-isolate-requests-in-another-class
 */

public class RequestManager {
    private static RequestManager instance;

    private RequestQueue queue;
    private String baseUrl;

    private RequestManager(Context context) {
        queue = Volley.newRequestQueue(context);

        // THIS IS MY LAPTOP'S IP ADDRESS, IT MIGHT CHANGE!!
        baseUrl =  "http://174.93.56.12:1234/";
    }

    public static synchronized RequestManager getInstance(Context context) {
        if (instance == null) {
            instance = new RequestManager(context);
        }

        return instance;
    }

    public static synchronized RequestManager getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Class not initialized, call getInstance(Context) first.");
        }

        return instance;
    }

    /* Private method to handle requests, public methods just pass along the necessary parmeters */
    private void sendAsynchronousRequest(int method, String url, JSONObject params, final ResponseListener<JSONObject> listener) {
        Response.Listener<JSONObject> responseListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (response != null) {
                    listener.getResult(response);
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.networkResponse != null) {
                    try {
                        listener.getResult(new JSONObject("{}"));
                    } catch (JSONException e) {
                        // TODO: handle JSON errors
                    }
                }
            }
        };

        JsonObjectRequest request = new JsonObjectRequest(method, url, params, responseListener, errorListener);

        queue.add(request);
    }

    private void sendBlockingRequest(int method, String url, JSONObject params, final ResponseListener<JSONObject> listener) {
        RequestFuture<JSONObject> future = RequestFuture.newFuture();

        JsonObjectRequest request = new JsonObjectRequest(method, url, params, future, future);

        queue.add(request);

        try {
            JSONObject response = future.get(30, TimeUnit.SECONDS);

            listener.getResult(response);
        } catch (InterruptedException e) {
            // Exception handling
        } catch (ExecutionException e) {
            // Exception handling
        } catch (TimeoutException e) {
            // Exception handling
        }
    }

    /* [GET] GetUserDetails */
    public User getUserDetails(String username, String password) {
        final User user = new User();

        // Set up information for the request
        int method = Request.Method.GET;
        String url = baseUrl + "/user/details?username=" + username + "&password=" + password;

        sendBlockingRequest(method, url, null, new ResponseListener<JSONObject>() {
            @Override
            public void getResult(JSONObject jsonObject) {
                // TODO: convert JSON object into User object
            }
        });

        return user;
    }

    /* [POST] UpdateUserDetails */
    public void updateUserDetails(User userDetails) {
        // Set up information for the request
        int method = Request.Method.POST;
        String url = baseUrl + "/user/upload";
        JSONObject params = null;

        try {
            params.put("username", userDetails.toString());
        } catch (JSONException e) {
            System.err.println("JSONException: " + e.getMessage());
        }

        sendAsynchronousRequest(method, url, params, new ResponseListener<JSONObject>() {
            @Override
            public void getResult(JSONObject jsonObject) {
                // TODO: does anything even need to go here or is this useless?
            }
        });
    }

    /* [POST] UploadEvent */
    public void uploadEvent(Event eventDetails) {
        // Set up information for the request
        int method = Request.Method.POST;
        String url = baseUrl + "/creator/upload-event";
        JSONObject params = null;

        try {
            // TODO: turn eventDetails into a JSON object
            params.put("blah", eventDetails.toString());
        } catch (JSONException e) {
            System.err.println("JSONException: " + e.getMessage());
        }

        sendAsynchronousRequest(method, url, params, new ResponseListener<JSONObject>() {
            @Override
            public void getResult(JSONObject jsonObject) {
                // TODO: does anything even need to go here or is this useless?
            }
        });
    }

    /* [POST] UploadMap */
    public void uploadMap(Map mapDetails) {
        // Set up information for the request
        int method = Request.Method.POST;
        String url = baseUrl + "/creator/upload-event";
        JSONObject params = null;

        try {
            // TODO: turn mapDetails into a JSON object
            params.put("blah", mapDetails.toString());
        } catch (JSONException e) {
            System.err.println("JSONException: " + e.getMessage());
        }

        sendAsynchronousRequest(method, url, params, new ResponseListener<JSONObject>() {
            @Override
            public void getResult(JSONObject jsonObject) {
                // TODO: does anything even need to go here or is this useless?
            }
        });
    }

    /* [GET] GetMapList */
    public ArrayList<Map> getMapList() {
        final ArrayList<Map> maps = new ArrayList<>();

        // Set up information for the request
        int method = Request.Method.GET;
        String url = baseUrl + "/player/get-map-list";

        sendBlockingRequest(method, url, null, new ResponseListener<JSONObject>() {
            @Override
            public void getResult(JSONObject jsonObject) {
                // TODO: convert JSON object into ArrayList<Map> object
            }
        });

        return maps;
    }

    /* [GET] GetMapDetails */
    public Map getMapDetails(String mapName) {
        final Map map = new Map();

        // Set up information for the request
        int method = Request.Method.GET;
        String url = baseUrl + "/player/get-map?map-name=" + mapName;

        sendBlockingRequest(method, url, null, new ResponseListener<JSONObject>() {
            @Override
            public void getResult(JSONObject jsonObject) {
                // TODO: convert JSON object into Map object
            }
        });

        return map;
    }

    /* [GET] GetRandomEvent */
    public Event getRandomEvent() {
        final Event event = new Event();

        // Set up information for the request
        int method = Request.Method.GET;
        String url = baseUrl + "/player/get-event";

        sendBlockingRequest(method, url, null, new ResponseListener<JSONObject>() {
            @Override
            public void getResult(JSONObject jsonObject) {
                // TODO: convert JSON object into Event object
            }
        });

        return event;
    }

    /* [POST] RateMap */
    public void rateMap(String mapName, int rating) {
        // Set up information for the request
        int method = Request.Method.POST;
        String url = baseUrl + "/creator/upload-event";
        JSONObject params = null;

        try {
            params.put("map-name", mapName);
            params.put("rating", rating);
        } catch (JSONException e) {
            System.err.println("JSONException: " + e.getMessage());
        }

        sendAsynchronousRequest(method, url, params, new ResponseListener<JSONObject>() {
            @Override
            public void getResult(JSONObject jsonObject) {
                // TODO: does anything even need to go here or is this useless?
            }
        });
    }
}
