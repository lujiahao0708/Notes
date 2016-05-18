package com.android.volley.plus;

import android.text.TextUtils;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Extended from the default Request provided by volley source with difference
 * as follows: 1 Default successListener. 2 Unified interface for get or post
 * request. Both can add params as a constructor param. As for get, extra params
 * will be appended to the original url. 3 Params is also crucial when
 * generating a cache key, to evade improper cache when requests differ only
 * from params. 4 The method getEncodedParameters can be used by sub classes.
 * User: Rodrigo Date: 13-11-8 Time: 下午4:32
 */
public abstract class RequestPro<T> extends Request<T> {
	private Response.Listener<T> successListener;
	private Map<String, String> params;

	public RequestPro(int method, String url, Map<String, String> params, Response.Listener<T> successListener,
			Response.ErrorListener errorListener) {
		super(method, url, errorListener);
		this.successListener = successListener;
		this.params = params;
	}

	private String mergeGetUrl() {
		String url = super.getUrl();
		if (url.endsWith("?"))
			return url + getEncodedParameters(params, getParamsEncoding());
		else
			return super.getUrl() + "?" + getEncodedParameters(params, getParamsEncoding());
	}

	/**
	 * For get request, add params if exists to the end or url.
	 * 
	 * @return merged url for get request.
	 */
	@Override
	public String getUrl() {
		if (getMethod() == Method.GET || getMethod() == Method.DELETE) {
			return mergeGetUrl();
		} else {
			return super.getUrl();
		}
	}

	/**
	 * Subclasses must implement this to parse the raw network response and
	 * return an appropriate response type. This method will be called from a
	 * worker thread. The response will not be delivered if you return null.
	 * 
	 * @param response
	 *            Response from the network
	 * @return The parsed response, or null in the case of an error
	 */
	@Override
	protected Response<T> parseNetworkResponse(NetworkResponse response) {
		return null;
	}

	/**
	 * Subclasses must implement this to perform delivery of the parsed response
	 * to their listeners. The given response is guaranteed to be non-null;
	 * responses that fail to parse are not delivered.
	 * 
	 * @param response
	 *            The parsed response returned by
	 *            {@link #parseNetworkResponse(com.android.volley.NetworkResponse)}
	 */
	@Override
	protected void deliverResponse(T response) {
		successListener.onResponse(response);
	}

	/**
	 * Returns a Map of parameters to be used for a POST or PUT request. Can
	 * throw {@link com.android.volley.AuthFailureError} as authentication may
	 * be required to provide these values.
	 * <p/>
	 * <p>
	 * Note that you can directly override {@link #getBody()} for custom data.
	 * </p>
	 * 
	 * @throws com.android.volley.AuthFailureError
	 *             in the event of auth failure
	 */
	@Override
	protected Map<String, String> getParams() throws AuthFailureError {
		return params;
	}

	/**
	 * Returns the cache key for this request. By default, this is the URL.
	 */
	@Override
	public String getCacheKey() {
		try {
			return getUrl() + "?" + getEncodedParameters(getParams(), getParamsEncoding());
		} catch (AuthFailureError authFailureError) {
			authFailureError.printStackTrace();
			return getUrl();
		}
	}

	/**
	 * Returns the raw POST or PUT body to be sent.
	 * 
	 * @throws com.android.volley.AuthFailureError
	 *             in the event of auth failure
	 */
	@Override
	public byte[] getBody() throws AuthFailureError {
		Map<String, String> params = getParams();
		if (params != null && params.size() > 0) {
			return getEncodedParameters(params, getParamsEncoding()).getBytes();
		}
		return null;
	}

	/**
	 * Converts <code>params</code> into an application/x-www-form-urlencoded
	 * encoded string.
	 */
	protected static String getEncodedParameters(Map<String, String> params, String paramsEncoding) {
		if (params == null || params.isEmpty())
			return "";
		StringBuilder encodedParams = new StringBuilder();
		try {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				if (TextUtils.isEmpty(entry.getKey()) || TextUtils.isEmpty(entry.getValue()))
					continue;
				encodedParams.append(URLEncoder.encode(entry.getKey(), paramsEncoding));
				encodedParams.append('=');
				encodedParams.append(URLEncoder.encode(entry.getValue(), paramsEncoding));
				encodedParams.append('&');
			}
            encodedParams.deleteCharAt(encodedParams.length() - 1);
			return encodedParams.toString();
		} catch (UnsupportedEncodingException uee) {
			throw new RuntimeException("Encoding not supported: " + paramsEncoding, uee);
		}
	}

}
