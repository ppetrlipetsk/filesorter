package FilesTools;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.imaging.jpeg.JpegSegmentMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifReader;
import com.drew.metadata.iptc.IptcReader;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

public class IPTCTools {

    public static Date strToDate(String dateString){
        Date dat1;
        DateFormat format = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss", Locale.ENGLISH);
        Date date = null;
        try {
            date = format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //System.out.println(date.toString()); // Sat Jan 02 00:00:00 GMT 2010
        return date;
    }

    public static String getCreationDateTime(Metadata metadata){
        String res="";

        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                //System.out.println(tag);
                String description = tag.getDescription();

                if (description == null)
                    description = tag.getTagType() + " (unable to formulate description)";
                String tagName=tag.getTagName();
                if (tagName.equals("Date/Time")) {
                    res=description;
                    break;
                }
                //return "[" + tag.getDirectoryName() + "] " + tag.getTagName() + " - " + description;
            }
        }
        return res;
    }

    public static Date getCreationDate(String fileName){
        File file=new File(fileName);
        return getCreationDate(file);
    }

    public static Date getCreationDate(File file){
        Iterable<JpegSegmentMetadataReader> readers = Arrays.asList(new ExifReader(), new IptcReader());
        Metadata metadata = null;
        Date datFile=null;
        try {
            metadata = JpegMetadataReader.readMetadata(file, readers);
            datFile=strToDate(getCreationDateTime(metadata));
        } catch (JpegProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return datFile;
    }


}
