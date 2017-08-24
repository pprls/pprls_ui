package org.pprls.ui.views.converters;

import com.vaadin.data.Converter;
import com.vaadin.data.Result;
import com.vaadin.data.ValueContext;

import java.io.File;
import java.net.URL;

public class FileToUrlConverter implements Converter<File, URL> {
  @Override
  public Result<URL> convertToModel(File file, ValueContext context) {
    // Produces a converted value or an error
    try {
      // ok is a static helper method that creates a Result
      return Result.ok(file.toURI().toURL());
    } catch (Exception e) {
      // error is a static helper method that creates a Result
      return Result.error("Please enter file");
    }
  }

  @Override
  public File convertToPresentation(URL url, ValueContext context) {
    // Converting to the field type should always succeed,
    // so there is no support for returning an error Result.
    return new File(url.getFile());
  }
}