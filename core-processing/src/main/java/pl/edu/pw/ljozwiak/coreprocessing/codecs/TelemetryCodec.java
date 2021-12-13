package pl.edu.pw.ljozwiak.coreprocessing.codecs;

import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import pl.edu.pw.ljozwiak.coreprocessing.model.Telemetry;

/** https://stackoverflow.com/questions/57659579/registering-codecs-for-mongodb-using-micronaut */
@RequiredArgsConstructor
public class TelemetryCodec implements Codec<Telemetry> {

  @Override
  public Telemetry decode(BsonReader reader, DecoderContext decoderContext) {
    reader.readStartDocument();

    Telemetry telemetry =
        Telemetry.builder()
            .id(reader.readString())
            .time(Instant.ofEpochMilli(reader.readDateTime()))
            .speed(reader.readInt32())
            .fuelLevel(reader.readDouble())
            .build();

    reader.readEndDocument();

    return telemetry;
  }

  @Override
  public void encode(BsonWriter writer, Telemetry value, EncoderContext encoderContext) {
    writer.writeStartDocument();
    writer.writeString("_id", value.getId());
    writer.writeDateTime("time", value.getTime().toEpochMilli());
    writer.writeInt32("speed", value.getSpeed());
    writer.writeDouble("fuelLevel", value.getFuelLevel());
    writer.writeEndDocument();
  }

  @Override
  public Class<Telemetry> getEncoderClass() {
    return Telemetry.class;
  }
}
