package pl.edu.pw.ljozwiak.coreprocessing.codecs;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import pl.edu.pw.ljozwiak.coreprocessing.model.Report;

public class ReportCodec implements Codec<Report> {

  @Override
  public Report decode(BsonReader reader, DecoderContext decoderContext) {
    reader.readStartDocument();

    Report report =
        Report.builder().id(reader.readString()).averageSpeed(reader.readDouble()).build();

    reader.readEndDocument();

    return report;
  }

  @Override
  public void encode(BsonWriter writer, Report value, EncoderContext encoderContext) {
    writer.writeStartDocument();
    writer.writeString("_id", value.getId());
    writer.writeDouble("averageSpeed", value.getAverageSpeed());
    writer.writeEndDocument();
  }

  @Override
  public Class<Report> getEncoderClass() {
    return Report.class;
  }
}
