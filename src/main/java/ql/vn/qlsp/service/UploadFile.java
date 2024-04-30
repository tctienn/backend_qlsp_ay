package ql.vn.qlsp.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
// upload file on google cloud
public class UploadFile {
    private String generateFileName(MultipartFile multipartFile) {
        return System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename().replace(" ", "_");  // tạo tên file
    }

    public String uploadFile(MultipartFile multipartFile) throws IOException {
        // sử lý và lấy tên ảnh
        String objectName = generateFileName(multipartFile);

        StorageOptions storageOptions = StorageOptions.newBuilder()
                .setProjectId("my-project")
                .setCredentials(GoogleCredentials
                        .fromStream(new ClassPathResource("ay.json").getInputStream()))  // file kết nối sdk của bọn google firebase
                .build();
        Storage storage = storageOptions.getService();
        BlobId blobId = BlobId.of("staging.testupload-93817.appspot.com", objectName);

        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(multipartFile.getContentType()).build();

        Blob blob = storage.create(blobInfo, multipartFile.getBytes());

//        System.out.println("UPLOAD FILE" + multipartFile.getName() + " " + multipartFile.getSize() + " octet");
        String fileUrl = blob.getMediaLink();

//        System.out.println("ay : " + blob);

//        return fileUrl;  // cái này là đường dẫn tải về

        return "https://storage.googleapis.com/" + blob.getBucket() + "/" + blob.getName();

    }

    // trích xuất tên ảnh từ url
    public String extractImageFileName(String imageUrl) {
        // Kiểm tra xem URL có null hay không
        if (imageUrl == null || imageUrl.isEmpty()) {
            return null;
        }

        // Tách URL bằng dấu "/"
        String[] urlParts = imageUrl.split("/");

        // Lấy phần cuối cùng của mảng, đó là tên tệp ảnh
        String fileName = urlParts[urlParts.length - 1];

        return fileName;
    }

    public String removeFile(String urlImg) throws IOException {
        String fileName = extractImageFileName(urlImg);
        String bucketName = "staging.testupload-93817.appspot.com";

        StorageOptions storageOptions = StorageOptions.newBuilder()
                .setProjectId("my-project")
                .setCredentials(GoogleCredentials
                        .fromStream(new ClassPathResource("ay.json").getInputStream()))  // file kết nối sdk của bọn google firebase
                .build();
        Storage storage = storageOptions.getService();


        Blob blob = storage.get(bucketName, fileName);  // đối tượng đại diện cho tệp tin
        if (blob == null) {
//            System.out.println("The object " + objectName + " wasn't found in " + bucketName);
            return "The object " + fileName + " wasn't found in " + bucketName;
        }

        Storage.BlobSourceOption precondition =
                Storage.BlobSourceOption.generationMatch(blob.getGeneration());  // Điều kiện này yêu cầu rằng số thế hệ của đối tượng phải khớp với giá trị đã cho (trong trường hợp này là giá trị trả về bởi phương thức getGeneration của đối tượng blob) trước khi xóa.

        storage.delete(bucketName, fileName, precondition);
        return "remove thành công";
    }
}
