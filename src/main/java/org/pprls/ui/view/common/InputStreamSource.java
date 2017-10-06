package org.pprls.ui.view.common;

import com.vaadin.server.StreamResource;

import java.io.InputStream;

// boilerplate StreamSource implementation
class InputStreamSource implements StreamResource.StreamSource {

        private final InputStream data;

        public InputStreamSource(InputStream data) {
            super();
            this.data = data;
        }

        @Override
        public InputStream getStream() {
            return data;
        }

    }