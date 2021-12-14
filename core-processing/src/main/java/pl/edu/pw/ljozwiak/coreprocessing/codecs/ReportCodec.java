package pl.edu.pw.ljozwiak.coreprocessing.codecs;

import com.mongodb.MongoClientSettings;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import pl.edu.pw.ljozwiak.coreprocessing.model.Report;

/** https://quarkus.io/guides/mongodb#simplifying-mongodb-client-usage-using-bson-codec */
public class ReportCodec implements Codec<Report> {

  private final Codec<Document> documentCodec;

  public ReportCodec() {
    this.documentCodec = MongoClientSettings.getDefaultCodecRegistry().get(Document.class);
  }

  @Override
  public Report decode(BsonReader reader, DecoderContext decoderContext) {
    Document document = documentCodec.decode(reader, decoderContext);

    return Report.builder()
        .id(document.getString("_id"))
        .averageSpeed(document.getDouble("averageSpeed"))
        .build();
  }

  @Override
  public void encode(BsonWriter writer, Report value, EncoderContext encoderContext) {
    Document doc = new Document();
    doc.put("_id", value.getId());
    doc.put("averageSpeed", value.getAverageSpeed());
    documentCodec.encode(writer, doc, encoderContext);
  }

  @Override
  public Class<Report> getEncoderClass() {
    return Report.class;
  }
}
