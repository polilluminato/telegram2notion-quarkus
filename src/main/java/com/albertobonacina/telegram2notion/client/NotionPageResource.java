package com.albertobonacina.telegram2notion.client;

import com.albertobonacina.telegram2notion.model.NotionPage;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Slf4j
@Path("/pages")
public class NotionPageResource {

    @RestClient
    NotionPageService notionPageService;

    @POST
    public void createNewPage(NotionPage notionPage){
        notionPageService.createNewPage(notionPage);
    }
}
