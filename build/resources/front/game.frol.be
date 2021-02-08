
<VirtualHost *:80>

	ServerName game.frol.be

	LoadModule proxy_module modules/mod_proxy.so
	LoadModule proxy_ajp_module modules/mod_proxy_ajp.so
	LoadModule proxy_http_module modules/mod_proxy_http.so
	LoadModule proxy_html_module modules/mod_proxy_html.so
	LoadModule proxy_connect_module modules/mod_proxy_connect.so
	LoadModule rewrite_module modules/mod_rewrite.so
	LoadModule deflate_module modules/mod_deflate.so
	LoadModule rewrite_module modules/mod_rewrite.so

	
	RewriteEngine On

	RewriteCond %{DOCUMENT_ROOT}%{REQUEST_URI} -f [OR]
	RewriteCond %{DOCUMENT_ROOT}%{REQUEST_URI} -d
	RewriteCond %{REQUEST_URI} !^/api/.*$
	RewriteRule ^ - [L]


	RewriteCond %{REQUEST_URI} !^/api/.*$
   	RewriteRule ^ /index.html [L]

  	ProxyPass /api http://back:9000/api
 	ProxyPassReverse /api http://back:9000/api

</VirtualHost>

