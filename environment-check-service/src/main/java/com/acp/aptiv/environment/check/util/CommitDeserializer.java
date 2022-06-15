package com.acp.aptiv.environment.check.util;

import com.acp.aptiv.environment.check.dto.util.Commit;
import com.acp.aptiv.environment.check.dto.util.Id;
import com.acp.aptiv.environment.check.dto.util.Message;
import com.acp.aptiv.environment.check.dto.util.User;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;

public class CommitDeserializer extends StdDeserializer<Commit> {

  private ObjectMapper mapper = new ObjectMapper();

  public CommitDeserializer() {
    this(Commit.class);
  }

  protected CommitDeserializer(Class<?> vc) {
    super(vc);
  }

  protected CommitDeserializer(JavaType valueType) {
    super(valueType);
  }

  protected CommitDeserializer(StdDeserializer<?> src) {
    super(src);
  }

  @Override
  public Commit deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
    JsonNode node = jp.getCodec().readTree(jp);

    JsonNode idNode = node.get("id");

    var commit = new Commit();
    commit.setTime(node.get("time").asText());
    if (idNode.isTextual()) {
      commit.setId(new Id(idNode.asText(), idNode.asText()));
    } else {
      commit.setId(mapper.convertValue(idNode, Id.class));
      commit.setUser(mapper.convertValue(node.get("user"), User.class));
      commit.setMessage(mapper.convertValue(node.get("message"), Message.class));
    }
    return commit;
  }
}
