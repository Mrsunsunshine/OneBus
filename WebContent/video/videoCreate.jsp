<%@page import="java.io.*"%>
<%
	try {
		String ppath = request.getParameter("ppath");
		String file = "C:/" + ppath;
		FileInputStream in = new FileInputStream(new File(file));
		OutputStream o = response.getOutputStream();
		int l = 0;
		byte[] buffer = new byte[4096];
		while ((l = in.read(buffer)) != -1) {
			o.write(buffer, 0, l);
		}
		o.flush();
		in.close();
		o.close();
	} catch (Exception e) {
	}
%>