package org.lsj.gs.protocol;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.lsj.gs.exception.GSException;
import org.lsj.utils.JsonUtil;
import org.lsj.websocket.ProtocolCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

public class CTSCommand {

    private static final Logger LOG = LoggerFactory.getLogger(CTSCommand.class);

    private Command command;
    private JsonNode dataJsonNode;

    public CTSCommand(Command command, JsonNode dataJsonNode) {
        this.command = command;
        this.dataJsonNode = dataJsonNode;
    }

    public Command getCommand() {
        return command;
    }

    public JsonNode getDataJsonNode() {
        return dataJsonNode;
    }

    public static CTSCommand parseByCtsMessage(String message) throws GSException {
        final JsonNode messageJsonNode;
        try {
            messageJsonNode = JsonUtil.getInstance().getObjectMapper().readTree(message);
        } catch (JsonProcessingException e) {
            LOG.warn("parse message error, message: {}", message, e);
            throw new GSException(ProtocolCode.FAIL, Level.WARN, "");
        }

        final JsonNode command = messageJsonNode.get("command");
        if (command == null) {
            LOG.warn("no command, message: {}", message);
            throw new GSException(ProtocolCode.FAIL, Level.WARN, "");
        }

        final JsonNode data = messageJsonNode.get("data");
        if (data == null) {
            LOG.warn("no data, message: {}", message);
            throw new GSException(ProtocolCode.FAIL, Level.WARN, "");
        }

        return new CTSCommand(Command.getCommandByCtsCommand(command.asText()), data);
    }

}
