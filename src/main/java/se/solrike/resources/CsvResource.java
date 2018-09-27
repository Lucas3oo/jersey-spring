package se.solrike.resources;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import org.springframework.stereotype.Component;

@Component
@Path("/csv")
public class CsvResource {

  @GET
  @Produces("application/csv;charset=UTF-16")
  public Response getCsv() {
    StreamingOutput streamingOutput = new StreamingOutput() {
      public void write(OutputStream output) throws IOException, WebApplicationException {
        OutputStreamWriter writer = new OutputStreamWriter(output, StandardCharsets.UTF_16LE.newEncoder());
        writer.write('\ufeff'); // since UTF_16LE is used we need to "reverse"
                                // the BOM, the BOM for UTF_16LE is actually fffe
        writer.write("Ärkel"); // The generated file shall have the following hex:
                               // fffe c400 7200 6b00 6500 6c00
                               // BOM  Ä    r    k    e    l
        writer.flush();
      }
    };
    return Response.ok(streamingOutput)
        .header("Content-Disposition", String.format("attachment; filename=\"%s\"", "some.csv")).build();
  }
}
