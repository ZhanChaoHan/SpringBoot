package com.sinosoft.normal.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {
	
	//无参get请求
	public static String doGet(String url){
		try {
			//创建一个httpclient对象
			CloseableHttpClient httpClient = HttpClients.createDefault();
			//创建一个GET对象
			HttpGet get =new HttpGet(url);
			//执行请求
			CloseableHttpResponse response;
			response = httpClient.execute(get);
			//取响应的结果
			int statusCode = response.getStatusLine().getStatusCode();
			HttpEntity entity = response.getEntity();
			String string = EntityUtils.toString(entity,"utf-8");
			//关闭httpclient
			response.close();
			httpClient.close();
			return string;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	//有参get请求
	public static String doGetWithParam(String url,Map<String,String> params){
		try {
			//创建一个httpclient对象
			CloseableHttpClient httpClient = HttpClients.createDefault();
			//创建一个uri对象
			URIBuilder uriBuilder;
			uriBuilder = new URIBuilder(url);
			if(!params.isEmpty()){
				Set<String> keys = params.keySet();
				for(String key:keys) {
					uriBuilder.addParameter(key,params.get(key));
				}
			}
			HttpGet get = new HttpGet(uriBuilder.build());
			//执行请求
			CloseableHttpResponse response =httpClient.execute(get);
			//取响应的结果
			int statusCode =response.getStatusLine().getStatusCode();
			HttpEntity entity =response.getEntity();
			String string = EntityUtils.toString(entity,"utf-8");
			//关闭httpclient
			response.close();
			httpClient.close();
			return string;
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		}
	
	//无参post请求
	public static String doPost(String url){
		try {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			//创建一个post对象
			HttpPost post =new HttpPost(url);
			//执行post请求
			CloseableHttpResponse response;
			response = httpClient.execute(post);
			String string = EntityUtils.toString(response.getEntity());
			response.close();
			httpClient.close();
			return string;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		}
	
	//有参post请求
	public static String doPostWithParam(String url, Map<String,String> params){
		StringEntity entity;
		try {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			//创建一个post对象
			HttpPost post =new HttpPost(url);
			//创建一个Entity。模拟一个表单
			List<NameValuePair>kvList = new ArrayList<NameValuePair>();
			if(!params.isEmpty()){
				Set<String> keys = params.keySet();
				for(String key:keys) {
					kvList.add(new BasicNameValuePair(key,params.get(key)));
				}
			}
			//包装成一个Entity对象
			entity = new UrlEncodedFormEntity(kvList,"utf-8");
			//设置请求的内容
			post.setEntity(entity);
			//执行post请求
			CloseableHttpResponse response =httpClient.execute(post);
			String string = EntityUtils.toString(response.getEntity());
			response.close();
			httpClient.close();
			return string;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
		//有参post请求
		public static String doPostUseJsonParams(String url, String params){
			try {
				CloseableHttpClient httpClient = HttpClients.createDefault();
				HttpPost httpPost = new HttpPost(url);
				httpPost.addHeader("Content-Type", "application/json");
				httpPost.setEntity(new StringEntity(params));

				CloseableHttpResponse response = httpClient.execute(httpPost);
				HttpEntity entity = response.getEntity();
				String responseContent = EntityUtils.toString(entity, "UTF-8"); 
				
				response.close();
				httpClient.close();
				return responseContent;
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}

}
