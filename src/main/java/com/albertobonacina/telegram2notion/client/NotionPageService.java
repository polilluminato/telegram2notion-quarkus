package com.albertobonacina.telegram2notion.client;

import com.albertobonacina.telegram2notion.model.NotionPage;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/pages")
@RegisterRestClient(configKey="notionclient-api")
@ClientHeaderParam(name = "Authorization", value = "${notion.secret}")
@ClientHeaderParam(name = "notion-version", value = "${notion.version}")
public interface NotionPageService {

    @POST
    Response createNewPage(NotionPage notionPage);
}
