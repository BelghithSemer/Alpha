package com.esprit.unibackend.services;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.files.WriteMode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DropboxService {
    private final DbxClientV2 dropboxClient;
    private static final Logger logger = LoggerFactory.getLogger(DropboxService.class);

    public DropboxService(@Value("${dropbox.access.token}") String accessToken) {
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/spring-boot-integration").build();
        this.dropboxClient = new DbxClientV2(config, accessToken);
    }

    public FileMetadata uploadFile(String path, InputStream inputStream) throws DbxException, IOException {
        return dropboxClient.files().uploadBuilder(path)
                .withMode(WriteMode.OVERWRITE)
                .uploadAndFinish(inputStream);
    }

    public void uploadFileTwo(MultipartFile file, String filePath) throws IOException, DbxException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(file.getBytes());
        Metadata uploadMetaData = dropboxClient.files().uploadBuilder(filePath).uploadAndFinish(inputStream);
        logger.info("upload meta data =====> {}", uploadMetaData.toString());
        inputStream.close();
    }
    public void downloadFile(String path, OutputStream outputStream) throws DbxException, IOException {
        dropboxClient.files().download(path).download(outputStream);
    }
    public List<Map<String, Object>> getFileList(String target) throws IOException, DbxException {
        // target
        List<Metadata> entries = dropboxClient.files().listFolder(target).getEntries();
        List<Map<String, Object>> result = new ArrayList<>();

        for (Metadata entry : entries ) {
            if (entry instanceof FileMetadata) {
                logger.info("{} is file", entry.getName());
            }
            String metaDataString = entry.toString();
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> map = new HashMap<>();
            map = mapper.readValue(metaDataString, new TypeReference<Map<String, Object>>(){});
            result.add(map);
//			if ("file".equals(map.get(".tag"))) {
//				GetTemporaryLinkResult temp = dropboxClient.files().getTemporaryLink(entry.getPathLower());
//				logger.info("thumbnail ==> {}", temp);
//			}
        }

        return result;
    }


}
