package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Company;
import models.Computer;
import play.Logger;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import service.JsonTestService;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Date;

/**
 * Created by admin on 17/6/15.
 */
public class JsonTestController extends Controller{
    public void setJsonTestService(JsonTestService jsonTestService) {
        this.jsonTestService = jsonTestService;
    }

    @Inject
    private JsonTestService jsonTestService;


    @BodyParser.Of(BodyParser.Json.class)
    public Result getRequest() {
        JsonNode json = request().body().asJson();
        return ok(json);
    }
    @BodyParser.Of(BodyParser.Json.class)
    public  Result queryComputer(){
        JsonNode json = request().body().asJson();
        Computer computer=this.jsonTestService.queryComputer(json.get("id").asInt());
        return ok(Json.toJson(computer));
    }
    @BodyParser.Of(BodyParser.Json.class)
    public  Result addComputer(){
        ObjectNode result= Json.newObject();
        Computer computer= new Computer();
        JsonNode node=request().body().asJson();
        computer.setName(node.get("name").asText());
        computer.setDiscontinued(new Date());
        computer.setIntroduced(new Date());
        Company company=new Company();
        company.setId(node.get("company_id").asLong());
        computer.setCompany(company);
        this.jsonTestService.create(computer);
        return ok(result.put("status","success"));
    }
    @BodyParser.Of(BodyParser.Json.class)
    public Result updateComputer(){
        ObjectNode result= Json.newObject();
        ObjectMapper mapper=new ObjectMapper();
        JsonNode node=request().body().asJson();
        Computer computer=new Computer();
        computer.setId(node.get("id").asLong());
        computer.setName(node.get("name").asText());
        computer.setDiscontinued(new Date());
        computer.setIntroduced(new Date());
        Company company=new Company();
        company.setId(node.get("company_id").asLong());
        computer.setCompany(company);
        this.jsonTestService.update(computer);
        return ok(result.put("status","success"));
    }
    @BodyParser.Of(BodyParser.Json.class)
    public Result delele(){
        JsonNode node=request().body().asJson();
        ObjectNode result= Json.newObject();
        this.jsonTestService.delete(node.get("id").asLong());
        return ok(result.put("status","success"));
    }

}
