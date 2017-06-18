package education.cs.scu.webSocket.handler;

import education.cs.scu.entity.UserDiagramData;

import javax.json.Json;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.io.StringWriter;

/**
 * Created by maicius on 2017/6/18.
 */
public class ServerEncoder implements Encoder.Text<UserDiagramData> {
    public String encode(UserDiagramData userDiagramData) throws EncodeException {
        StringWriter writer = new StringWriter();
        //Makes use of the JSON Streaming API to build the JSON string.
        Json.createGenerator(writer)
                .writeStartObject()
                .write("time", userDiagramData.getTime())
                .write("number", userDiagramData.getNumber())
                .writeEnd()
                .flush();
        System.out.println(writer.toString());
        return writer.toString();
    }

    public void init(EndpointConfig endpointConfig) {

    }

    public void destroy() {

    }
}
