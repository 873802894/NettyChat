import com.gy.pojo.bo.UsersBO;
import com.gy.utils.FastDFSClient;
import com.gy.utils.FileUtils;
import org.springframework.web.multipart.MultipartFile;

public class Test {
    public static void main(String[] args) throws Exception {
	UsersBO usersBO = new UsersBO();
	usersBO.setUserId("201114C3CFBA7W28");

	String base64Data = usersBO.getFaceData();
	String userFacePath = "/Users/mac/Desktop/face"+usersBO.getUserId()+"userface64.png";
	FileUtils.base64ToFile(userFacePath,base64Data);

	//上传文件到FDFS
	FastDFSClient fastDFSClient = new FastDFSClient();

	MultipartFile multipartFile = FileUtils.fileToMultipart(userFacePath);
	String url = fastDFSClient.uploadBase64(multipartFile);
	System.out.println(url);
    }
}
