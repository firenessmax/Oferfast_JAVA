# Oferfast_JAVA
=======


# Comandos
--------


Etiquetas
--------
Para obtener las etiquetas, existen las siguientes opciones

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

-Modificar etiqueta
<p>@PUT</p>
<p>/etiquetas</p>
```json
{
	"counter":3,"etiquetaId":1,"name":"<<nuevo_nombre_de_la_etiqueta>>","visibleEtiqueta":0
}
```





