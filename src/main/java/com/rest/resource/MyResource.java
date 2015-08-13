package com.rest.resource;

import io.swagger.annotations.Api;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import com.rest.model.MyEntity;

@Component
@Api(value = "/things", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
@Path("/things")
public class MyResource {

	/*
	 To invoke this endpoint:
	 
	 curl -v -X POST http://localhost:8095/things/test -d @myentity.json -H "Content-Type: application/json"

	 myentity.json
	 {"id": 100,"date": "2010-01-01T12:00:00+01:00" }
	*/
	
	
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/test")
    public Response helloPost(MyEntity somedata1) {
        return Response.status(200).entity(someData()).build();
    }

    private MyEntity someData() {
        final MyEntity data = new MyEntity();
        data.setId(10);
        data.setDate(new Date());
        return data;
    }
}
