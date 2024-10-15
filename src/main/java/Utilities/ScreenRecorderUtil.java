package Utilities;

import java.awt.AWTException;
import java.awt.GraphicsConfiguration;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import org.monte.screenrecorder.*;
import org.monte.media.Format;
import org.monte.media.Registry;

public class ScreenRecorderUtil extends ScreenRecorder {

	public ScreenRecorderUtil(GraphicsConfiguration cfg, Rectangle captureArea, Format fileFormat, Format screenFormat, Format mouseFormat, Format audioFormat, File movieFolder) throws IOException, AWTException {
        super(cfg, audioFormat, audioFormat, audioFormat, audioFormat);
    }

    @Override
    protected File createMovieFile(Format fileFormat) throws IOException {
        if (!movieFolder.exists()) {
            movieFolder.mkdirs();
        } else if (!movieFolder.isDirectory()) {
            throw new IOException("\"" + movieFolder + "\" is not a directory.");
        }

        return new File(movieFolder, "TestRecording_" + System.currentTimeMillis() + "." + Registry.getInstance().getExtension(fileFormat));
    }
}
