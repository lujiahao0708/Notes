# Android网络基础

## 使用HttpURLConnection还是HttpClient

## HttpClient
Apache提供的高效的网络访问模块。

Android 2.2(API 8)及以前使用HttpClient比价好，bug少。

但是在[最新的Android版本中移除了](http://developer.android.com/about/versions/marshmallow/android-6.0-changes.html#behavior-apache-http-client)，如果你还想使用这个，在`build.gradle`里面添加：
	
	android {
	    useLibrary 'org.apache.http.legacy'
	}

### 具体使用方法：

	String urlAddress = "http://192.168.1.102:8080/qualityserver/login.do";  
	// 发送GET请求
	public String doGet(String username,String password){  
	    String getUrl = urlAddress + "?username="+username+"&password="+password;  
	    HttpGet httpGet = new HttpGet(getUrl);  
	    HttpParams hp = httpGet.getParams();  
	    hp.getParameter("true");  
	    HttpClient hc = new DefaultHttpClient();  
	    try {  
	        HttpResponse ht = hc.execute(httpGet);  
	        if(ht.getStatusLine().getStatusCode() == HttpStatus.SC_OK){  
	            HttpEntity he = ht.getEntity();  
	            InputStream is = he.getContent();  
	            BufferedReader br = new BufferedReader(new InputStreamReader(is));  
	            String response = "";  
	            String readLine = null;  
	            while((readLine =br.readLine()) != null){  
	                //response = br.readLine();  
	                response = response + readLine;  
	            }  
	            is.close();  
	            br.close();  
	              
	            //String str = EntityUtils.toString(he);  
	            System.out.println("========="+response);  
	            return response;  
	        }else{  
	            return "error";  
	        }  
	    } catch (ClientProtocolException e) {  
	        e.printStackTrace();  
	        return "exception";  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	        return "exception";  
	    }      
	}  
	  
	public String doPost(String username,String password){  
	    //String getUrl = urlAddress + "?username="+username+"&password="+password;  
	    HttpPost httpPost = new HttpPost(urlAddress);  
	    List params = new ArrayList();  
	    NameValuePair pair1 = new BasicNameValuePair("username", username);  
	    NameValuePair pair2 = new BasicNameValuePair("password", password);  
	    params.add(pair1);  
	    params.add(pair2);  
	      
	    HttpEntity he;  
	    try {  
	        he = new UrlEncodedFormEntity(params, "gbk");  
	        httpPost.setEntity(he);  
	          
	    } catch (UnsupportedEncodingException e1) { 
	        e1.printStackTrace();  
	    }   
	      
	    HttpClient hc = new DefaultHttpClient();  
	    try {  
	        HttpResponse ht = hc.execute(httpPost);  
	        //连接成功  
	        if(ht.getStatusLine().getStatusCode() == HttpStatus.SC_OK){  
	            HttpEntity het = ht.getEntity();  
	            InputStream is = het.getContent();  
	            BufferedReader br = new BufferedReader(new InputStreamReader(is));  
	            String response = "";  
	            String readLine = null;  
	            while((readLine =br.readLine()) != null){  
	                //response = br.readLine();  
	                response = response + readLine;  
	            }  
	            is.close();  
	            br.close();  
	              
	            //String str = EntityUtils.toString(he);  
	            System.out.println("=========&&"+response);  
	            return response;  
	        }else{  
	            return "error";  
	        }  
	    } catch (ClientProtocolException e) {
	        e.printStackTrace();  
	        return "exception";  
	    } catch (IOException e) {
	        e.printStackTrace();  
	        return "exception";  
	    }     
	}

## HttpURLConnection
HttpURLConnection是一种多用途、轻量极的HTTP客户端，使用它来进行HTTP操作可以适用于大多数的应用程序。

### 注意事项
1. HttpURLConnection在Android 2.2及以前有一些bug。
2. 参看笔记：Android 4.0 Ice Cream Sandwich GET请求变POST请求


### 具体使用方法：

	String urlAddress = "http://192.168.1.102:8080/AndroidServer/login.do";  
    URL url;  
    HttpURLConnection uRLConnection;  
 
    //向服务器发送get请求
    public String doGet(String username,String password){  
        String getUrl = urlAddress + "?username="+username+"&password="+password;  
        try {  
            url = new URL(getUrl);  
            uRLConnection = (HttpURLConnection)url.openConnection();  
            InputStream is = uRLConnection.getInputStream();  
            BufferedReader br = new BufferedReader(new InputStreamReader(is));  
            String response = "";  
            String readLine = null;  
            while((readLine =br.readLine()) != null){  
                //response = br.readLine();  
                response = response + readLine;  
            }  
            is.close();  
            br.close();  
            uRLConnection.disconnect();  
            return response;  
        } catch (MalformedURLException e) {  
            e.printStackTrace();  
            return null;  
        } catch (IOException e) {  
            e.printStackTrace();  
            return null;  
        }  
    }  
      
    //向服务器发送post请求
    public String doPost(String username,String password){  
        try {  
            url = new URL(urlAddress);  
            uRLConnection = (HttpURLConnection)url.openConnection();  
            uRLConnection.setDoInput(true);  
            uRLConnection.setDoOutput(true);  
            uRLConnection.setRequestMethod("POST");  
            uRLConnection.setUseCaches(false);  
            uRLConnection.setInstanceFollowRedirects(false);  
            uRLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");  
            uRLConnection.connect();  
              
            DataOutputStream out = new DataOutputStream(uRLConnection.getOutputStream());  
            String content = "username="+username+"&password="+password;  
            out.writeBytes(content);  
            out.flush();  
            out.close();  
              
            InputStream is = uRLConnection.getInputStream();  
            BufferedReader br = new BufferedReader(new InputStreamReader(is));  
            String response = "";  
            String readLine = null;  
            while((readLine =br.readLine()) != null){  
                //response = br.readLine();  
                response = response + readLine;  
            }  
            is.close();  
            br.close();  
            uRLConnection.disconnect();  
            return response;  
        } catch (MalformedURLException e) {  
            e.printStackTrace();  
            return null;  
        } catch (IOException e) {  
            e.printStackTrace();  
            return null;  
        }  
    }

## 参考资料
http://blog.csdn.net/guolin_blog/article/details/12452307
