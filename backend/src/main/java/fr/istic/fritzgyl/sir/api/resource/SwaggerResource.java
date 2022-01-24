package fr.istic.fritzgyl.sir.api.resource;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/api")
public class SwaggerResource {

	@GET
	public byte[] Get1() {
		try {
			return Files.readAllBytes(FileSystems.getDefault().getPath("src/main/webapp/swagger/index.html"));
		} catch (IOException e) {
			return null;
		}
	}

	@GET
	@Path("{path:.*}")
	public byte[] Get(@PathParam("path") String path) {
		try {
			return Files.readAllBytes(FileSystems.getDefault().getPath("src/main/webapp/swagger/" + path));
		} catch (IOException e) {
			return null;
		}
	}

}