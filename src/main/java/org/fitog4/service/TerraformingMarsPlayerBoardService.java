package org.fitog4.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("playerboard")
public class TerraformingMarsPlayerBoardService {

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String getMsg() {
    return "You yo yo yo!!";
  }
}
