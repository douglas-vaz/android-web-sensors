package com.douglas.websensors;

import java.util.Map;

public class Server extends NanoHTTPD {
	
	private String[] data;
	
	public Server(int port)
	{
		super(port);
	}
	
	public void setData(String[] d)
	{
		data = d;
	}
	
	@Override
    public Response serve(String uri, Method method, Map<String, String> header, Map<String, String> parms, Map<String, String> files) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<head><title>Debug Server</title></head>");
        sb.append("<body>");
        
        for(String i:data)
        {
        	sb.append(i+"<br>");
        }
        
        sb.append("</body>");
        sb.append("</html>");
        return new Response(sb.toString());
    }

}
