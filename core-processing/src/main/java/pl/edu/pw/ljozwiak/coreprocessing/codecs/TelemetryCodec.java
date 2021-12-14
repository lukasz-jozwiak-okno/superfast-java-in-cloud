package pl.edu.pw.ljozwiak.coreprocessing.codecs;

import com.mongodb.MongoClientSettings;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import pl.edu.pw.ljozwiak.coreprocessing.model.Telemetry;

/** https://stackoverflow.com/questions/57659579/registering-codecs-for-mongodb-using-micronaut */
@RequiredArgsConstructor
public class TelemetryCodec implements Codec<Telemetry> {

  private final Codec<Document> documentCodec;

  public TelemetryCodec() {
    this.documentCodec = MongoClientSettings.getDefaultCodecRegistry().get(Document.class);
  }

  @Override
  public Telemetry decode(BsonReader reader, DecoderContext decoderContext) {
    Document document = documentCodec.decode(reader, decoderContext);

    return Telemetry.builder()
        .id(document.getString("_id"))
        .time(Instant.ofEpochMilli(document.getDate("time").getTime()))
        .speed(document.getInteger("speed"))
        .fuelLevel(document.getDouble("fuelLevel"))
        .build();
  }

  @Override
  public void encode(BsonWriter writer, Telemetry value, EncoderContext encoderContext) {
    Document doc = new Document();
    doc.put("_id", value.getId());
    doc.put("time", value.getTime());
    doc.put("speed", value.getSpeed());
    doc.put("fuelLevel", value.getFuelLevel());
    documentCodec.encode(writer, doc, encoderContext);
  }

  @Override
  public Class<Telemetry> getEncoderClass() {
    return Telemetry.class;
  }
}
