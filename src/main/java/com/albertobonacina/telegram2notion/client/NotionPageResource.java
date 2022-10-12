package com.albertobonacina.telegram2notion.client;

import com.albertobonacina.telegram2notion.model.NotionPage;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Slf4j
@Path("/pages")
public class NotionPageResource {

    @RestClient
    NotionPageService notionPageService;

    @POST
    public Response createNewPage(NotionPage notionPage){
        return notionPageService.createNewPage(notionPage);
    }
}
