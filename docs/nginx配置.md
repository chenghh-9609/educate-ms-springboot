下载安装nginx


nginx安装目录下conf/nginx.conf配置：
```
#user  nobody;
worker_processes  1;

#error_log  logs/error.log;
#error_log  logs/error.log  notice;
#error_log  logs/error.log  info;

#pid        logs/nginx.pid;


events {
    worker_connections  1024;
}


http {
    include       mime.types;
    default_type  application/octet-stream;

    #log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
    #                  '$status $body_bytes_sent "$http_referer" '
    #                  '"$http_user_agent" "$http_x_forwarded_for"';

    #access_log  logs/access.log  main;

    sendfile        on;
    #tcp_nopush     on;

    #keepalive_timeout  0;
    keepalive_timeout  65;

    #gzip  on;

    server {
        listen       5000;
        # listen       443 ssl;
        server_name  0.0.0.0;

        # ssl_certificate      /etc/nginx/ssl/cloud_phone.crt;
        # ssl_certificate_key  /etc/nginx/ssl/cloud_phone.key;


        #charset gbk,utf-8;

        #access_log  logs/host.access.log  main;
        #后端api地址    
        location /api {
            # set $access '';
			set $access $http_origin;
       
			proxy_pass http://192.168.94.68:5555;
			add_header Access-Control-Allow-Origin $access always;
			add_header Access-Control-Allow-Credentials true always;
			add_header Access-Control-Allow-Headers 'X-MVS-CSRF-TOKEN,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Token' always;
			add_header Access-Control-Allow-Methods 'GET, OPTIONS, POST, PATCH, PUT, DELETE' always;	    
			if ($request_method = 'OPTIONS') {
	    	    add_header Access-Control-Allow-Origin $access;
	    	    add_header Access-Control-Allow-Credentials true;
	    	    add_header Access-Control-Allow-Headers 'X-MVS-CSRF-TOKEN,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Token';
	    	    add_header Access-Control-Max-Age 86400;
	    	    add_header Access-Control-Allow-Methods 'GET, OPTIONS, POST, PATCH, PUT, DELETE';
	    	    add_header Content-Type 'text/plain charset=UTF-8';	    	
	    	    return 200;
			}
        }
        #前端项目配置
        location / {
				proxy_pass http://192.168.94.68:3000;
                #alias E:\学习\frontend-learn\react-educate-ms\build\public;
				#index index.html index.htm;
        }
        #静态文件地址配置
		#location /static {
        #    alias E:\学习\backend-learn\springboot-bms-demo\image;
		#	autoindex on;
        #}

    }


    # another virtual host using mix of IP-, name-, and port-based configuration
    #
    #server {
    #    listen       8000;
    #    listen       somename:8080;
    #    server_name  somename  alias  another.alias;

    #    location / {
    #        root   html;
    #        index  index.html index.htm;
    #    }
    #}


    # HTTPS server
    #
    #server {
    #    listen       443 ssl;
    #    server_name  localhost;

    #    ssl_certificate      cert.pem;
    #    ssl_certificate_key  cert.key;

    #    ssl_session_cache    shared:SSL:1m;
    #    ssl_session_timeout  5m;

    #    ssl_ciphers  HIGH:!aNULL:!MD5;
    #    ssl_prefer_server_ciphers  on;

    #    location / {
    #        root   html;
    #        index  index.html index.htm;
    #    }
    #}

}

```
