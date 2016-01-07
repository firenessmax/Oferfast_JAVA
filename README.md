# Oferfast_JAVA
=======


# Comandos
--------



Ofertas
--------

- Obtener una lista de ofertas
<p>@GET</p>
<p>/ofertas</p>
```json
[{
    "date": "2016-01-06T21:21:47.987938", "description": "description_1", "imagesNumber": 0, "ofertaId": 1, "price": 100, "title": "title_1", "ubicationLat": 510, "ubicationLon": 3200, "usuarioId": 1, "visibleOferta": 1
  },
  {
    "date": "2016-01-06T21:22:06.208709", "description": "description_2", "imagesNumber": 0, "ofertaId": 2, "price": 1000, "title": "title_2", "ubicationLat": 50, "ubicationLon": 200, "usuarioId": 1, "visibleOferta": 1
  }]
```

- Obtener una lista de ofertas visibles/no visibles
<p>@GET</p>
<p>/ofertas/{sino}</p>
sino, debe tener valor 1 (visible) o 0 (no visible)
```json
[{
    "date": "2016-01-06T21:21:47.987938", "description": "description_1", "imagesNumber": 0, "ofertaId": 1, "price": 100, "title": "title_1", "ubicationLat": 510, "ubicationLon": 3200, "usuarioId": 1, "visibleOferta": 1
  },
  {
    "date": "2016-01-06T21:22:06.208709", "description": "description_2", "imagesNumber": 0, "ofertaId": 2, "price": 1000, "title": "title_2", "ubicationLat": 50, "ubicationLon": 200, "usuarioId": 1, "visibleOferta": 1
  }]
```


- Obtener una oferta especifica
<p>@GET</p>
<p>/oferta/{id}</p>
```json
{
    "date": "2016-01-06T21:21:47.987938", "description": "description_1", "imagesNumber": 0, "ofertaId": 1, "price": 100, "title": "title_1", "ubicationLat": 510, "ubicationLon": 3200, "usuarioId": 1, "visibleOferta": 1
}
```

- Obtener las etiquetas que usa una oferta especifica
<p>@GET</p>
<p>/ofertas/{id}/etiquetas</p>
```json
[
  {
    "counter": 1, "etiquetaId": 1, "name": "tag_1", "visibleEtiqueta": 1
  },
  {
    "counter": 1, "etiquetaId": 2, "name": "tag_2", "visibleEtiqueta": 1
  },
  {
    "counter": 1, "etiquetaId": 3, "name": "tag_3", "visibleEtiqueta": 1
  },
  {
    "counter": 1, "etiquetaId": 4, "name": "cocina", "visibleEtiqueta": 1
  }
]
```

- Escribir oferta
<p>@POST</p>
<p>/ofertas</p>
```json
{
  "title": "title_1", "description": "description_1", "ubicationLon":10, "ubicationLat":20, "price":1000, "imagesNumber":0, "visibleOferta":1, "usuarioId":1
}
```

- Escribir oferta que posee etiquetas (el que usaremos)
<p>@POST</p>
<p>/ofertas/newOferta</p>
```json
{
  "title": "title_8", "description": "description_8", "ubicationLon":250, "ubicationLat":54, "price":1200, "imagesNumber":0, "visibleOferta":1, "usuarioId":2,
  "tags":["tag_1","tag_2","tag_2","cocina"]
}
```






Etiquetas
--------

- Obtener una lista de etiquetas
<p>@GET</p>
<p>/etiquetas</p>
```json
[{
	"counter":3,"etiquetaId":1,"name":"cocina","visibleEtiqueta":0
},{
	"counter":2,"etiquetaId":2,"name":"arte","visibleEtiqueta":0
},{
	"counter":1,"etiquetaId":3,"name":"deporte","visibleEtiqueta":0
}]
```

- Obtener una etiqueta especifica
<p>@GET</p>
<p>/etiquetas/{id}</p>
```json
{
	"counter":3,"etiquetaId":1,"name":"cocina","visibleEtiqueta":0
}
```

- Obtener las ofertas que usan una etiqueta especifica
<p>@GET</p>
<p>/etiquetas/{id}/ofertas</p>
```json
[{
    "date": "2015-12-28T11:26:58.800174", "description": "description_1", "imagesNumber": 0, "ofertaId": 1, "price": 1000, "title": "title_1", "ubicationLat": 20, "ubicationLon": 10,
    "usuario": {
      "email": "email_1", "password": "password_1", "permission": 1, "reputation": 1, "type": 1, "urlProfilePicture": "picture_1", "urlProfileThumbnail": "thumbnail_1", "username": "username_del_usuario_1", "usuarioId": 1, "visibleUsuario": 1
    },
    "usuarioId": 1, "visibleOferta": 1
},{
    "date": "2015-12-28T11:30:51.884603", "description": "description_2", "imagesNumber": 0, "ofertaId": 2, "price": 1300, "title": "title_2", "ubicationLat": 22, "ubicationLon": 12,
    "usuario": {
      "email": "email_1", "password": "password_1", "permission": 1, "reputation": 1, "type": 1, "urlProfilePicture": "picture_1", "urlProfileThumbnail": "thumbnail_1", "username": "username_del_usuario_1", "usuarioId": 1, "visibleUsuario": 1
    },
    "usuarioId": 1, "visibleOferta": 1
}]
```

- Escribir etiqueta
<p>@POST</p>
<p>/etiquetas</p>
```json
{
	"counter":0,"name":"<<nombre_de_la_etiqueta>>","visibleEtiqueta":0
}
```

- Modificar etiqueta
<p>@PUT</p>
<p>/etiquetas</p>
```json
{
	"counter":3,"etiquetaId":1,"name":"<<nuevo_nombre_de_la_etiqueta>>","visibleEtiqueta":0
}
```





Usuarios
--------

- Info del login
<p>@POST</p>
<p>/usuarios/login</p>
```json
{
    "username": "nombre_de_usuario",
    "password": "contraseña"
}
```
los JSON posibles son (dependiendo de si corresponden los datos):

```json
{
  "INFO": "Loggeado",
  "usuarioId": 2, "username": "user_2", "email": "email_2", "type": 1, "reputation": 1, "urlProfilePicture": "picture_2", 
  "urlProfileThumbnail": "thumbnail_2"
}

{
  "ERROR": "La constraseña no corresponde, vuelva a intentarlo"
}

{
  "ERROR": "No existe un usuario con ese username"
}
```