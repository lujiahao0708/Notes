# WebView

1. WebView发送普通请求

        public void loadUrl (String url)

2. WebView发送post请求

        public void postUrl (String url, byte[] postData)
        
        // 需要访问的网址
        String url = "http://www.cqjg.gov.cn/netcar/FindThree.aspx";
        //post访问需要提交的参数
        String postDate = "txtName=zzz&QueryTypeLst=1&CertificateTxt=dsds";
        //由于webView.postUrl(url, postData)中 postData类型为byte[]
        //通过EncodingUtils.getBytes(data, charset)方法进行转换
        webView.postUrl(url, EncodingUtils.getBytes(postDate, "BASE64"));

