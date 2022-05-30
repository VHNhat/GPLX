package team2.api.mobile.gplx.extension;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import team2.api.mobile.gplx.models.Account;
import team2.api.mobile.gplx.models.AccountStatus;
import team2.api.mobile.gplx.models.Answer;
import team2.api.mobile.gplx.models.License;
import team2.api.mobile.gplx.models.LicenseType;
import team2.api.mobile.gplx.models.Question;
import team2.api.mobile.gplx.models.QuestionSet;
import team2.api.mobile.gplx.models.QuestionType;
import team2.api.mobile.gplx.models.Role;
import team2.api.mobile.gplx.models.Status;
import team2.api.mobile.gplx.models.TrafficSign;
import team2.api.mobile.gplx.models.TrafficSignType;
import team2.api.mobile.gplx.repository.AccountRepository;
import team2.api.mobile.gplx.repository.AnswerRepository;
import team2.api.mobile.gplx.repository.LicenseRepository;
import team2.api.mobile.gplx.repository.LicenseTypeRepository;
import team2.api.mobile.gplx.repository.QuestionRepository;
import team2.api.mobile.gplx.repository.QuestionSetRepository;
import team2.api.mobile.gplx.repository.QuestionTypeRepository;
import team2.api.mobile.gplx.repository.RoleRepository;
import team2.api.mobile.gplx.repository.TrafficSignRepository;
import team2.api.mobile.gplx.repository.TrafficSignTypeRepository;

@Component
public class DataSeeding implements CommandLineRunner {

	@Autowired
	private LicenseTypeRepository licenseTypeRepo;
	@Autowired
	private LicenseRepository licenseRepo;
	@Autowired
	private RoleRepository roleRepo;
	@Autowired
	private AccountRepository accountRepo;
	@Autowired
	private TrafficSignTypeRepository trafficSignTypeRepo;
	@Autowired
	private TrafficSignRepository trafficSignRepo;
	@Autowired
	private QuestionTypeRepository questionTypeRepo;
	@Autowired
	private QuestionRepository questionRepo;
	@Autowired
	private QuestionSetRepository questionSetRepo;
	@Autowired
	private AnswerRepository answerRepo;
	
	@Override
	public void run(String... args) throws Exception {
		LoadRole();
		LoadAccount();
		LoadLicenseType();
		LoadLicense();
		LoadTrafficSignType();
		LoadTrafficSign();
		LoadQuestionSet();
		LoadQuestionType();
		LoadQuestion();
		LoadAnswer();
	}
	private void LoadRole() {
		if(roleRepo.count() == 0){
			Role admin = new Role("Admin", "Administrator");
			roleRepo.save(admin);
			Role user = new Role("User", "User");
			roleRepo.save(user);
		}
		
	}
	private void LoadAccount() {
		if(accountRepo.count() == 0) {
			Role admin = roleRepo.findByRoleName("Admin");
			Role user = roleRepo.findByRoleName("User");
			Account account1 = new Account("admin", "admin123", "admin@gmail.com", "Nhật", "Võ", "", AccountStatus.ACTIVE, admin.getId());
			accountRepo.save(account1);
			Account account2 = new Account("user", "user123", "user@gmail.com", "Nhật", "Võ", "", AccountStatus.ACTIVE, user.getId());
			accountRepo.save(account2);
		}
		
	}
	private void LoadLicenseType() {
		if(licenseTypeRepo.count() == 0) {
			LicenseType xe2Banh = new LicenseType("Xe mô tô", Status.ACTIVE, "Bằng lái xe mô tô");
			licenseTypeRepo.save(xe2Banh);
			LicenseType xe4Banh = new LicenseType("Xe ô tô", Status.ACTIVE, "Bằng lái xe ô tô");
			licenseTypeRepo.save(xe4Banh);
		}
		
	}
	private void LoadLicense() {
		if(licenseRepo.count() == 0) {
			LicenseType xe2Banh = licenseTypeRepo.findByName("Xe mô tô");
			License a1 = new License("A1", Status.ACTIVE, "Bằng lái xe A1", xe2Banh.getId());
			licenseRepo.save(a1);
			License a2 = new License("A2", Status.ACTIVE, "Bằng lái xe A2", xe2Banh.getId());
			licenseRepo.save(a2);
			LicenseType xe4Banh = licenseTypeRepo.findByName("Xe ô tô");
			License b1 = new License("B1", Status.ACTIVE, "Bằng lái xe B1", xe4Banh.getId());
			licenseRepo.save(b1);
			License b2 = new License("B2", Status.ACTIVE, "Bằng lái xe B2", xe4Banh.getId());
			licenseRepo.save(b2);
		}
		
	}
	private void LoadTrafficSignType() {
		if(trafficSignTypeRepo.count() == 0) {
			TrafficSignType prohibitionSign = new TrafficSignType("C", "Biển báo cấm");
			trafficSignTypeRepo.save(prohibitionSign);
			TrafficSignType dangerSign = new TrafficSignType("NH", "Biển báo nguy hiểm");
			trafficSignTypeRepo.save(dangerSign);
			TrafficSignType commandSign = new TrafficSignType("HL", "Biển báo hiệu lệnh");
			trafficSignTypeRepo.save(commandSign);
			TrafficSignType directionSign = new TrafficSignType("CD", "Biển báo chỉ dẫn");
			trafficSignTypeRepo.save(directionSign);
			TrafficSignType subSign = new TrafficSignType("P", "Biển báo phụ");
			trafficSignTypeRepo.save(subSign);
			TrafficSignType roadMarking = new TrafficSignType("V" ,"Vạch kẻ đường");
			trafficSignTypeRepo.save(roadMarking);
		}
	}
	private void LoadTrafficSign() {
		if(trafficSignRepo.count() == 0) {
			String probihitionSignId = trafficSignTypeRepo.findByCode("C").getId();
			String dangerSignId = trafficSignTypeRepo.findByCode("NH").getId();
			String commandSignId = trafficSignTypeRepo.findByCode("HL").getId();
			String directionSignId = trafficSignTypeRepo.findByCode("CD").getId();
			String subSignId = trafficSignTypeRepo.findByCode("P").getId();
			String roadMarkingId = trafficSignTypeRepo.findByCode("V").getId();
			// Biển báo cấm
			TrafficSign p101 = new TrafficSign("P.101", "Đường cấm", "Cấm các loại phương tiện di chuyển cả 2 hướng (ngoại trừ xe ưu tiên theo quy định).", "p101.png", probihitionSignId);
			trafficSignRepo.save(p101);
			TrafficSign p102 = new TrafficSign("P.102", "Cấm đi ngược chiều", "Cấm các loại phương tiện đi vào chiều đặt biển (ngoại trừ xe ưu tiên theo quy định).", "p102.png", probihitionSignId);
			trafficSignRepo.save(p102);
			TrafficSign p103a = new TrafficSign("P.103a", "Cấm ô tô", "Cấm các loại xe cơ giới đi vào, trừ xe máy 2 bánh, xe gắn máy và xe ưu tiên theo quy định.", "p103a.png", probihitionSignId);
			trafficSignRepo.save(p103a);
			TrafficSign p103b = new TrafficSign("P.103b", "Cấm ô tô rẽ phải", "Cấm các loại xe cơ giới rẽ phải, trừ xe máy 2 bánh, xe gắn máy và xe ưu tiên theo quy định.", "p103b.png", probihitionSignId);
			trafficSignRepo.save(p103b);
			TrafficSign p103c = new TrafficSign("P.103c", "Cấm ô tô rẽ trái", "Cấm các loại xe cơ giới rẽ trái, trừ xe máy 2 bánh, xe gắn máy và xe ưu tiên theo quy định.", "p103c.png", probihitionSignId);
			trafficSignRepo.save(p103c);
			TrafficSign p104 = new TrafficSign("P.104", "Cấm mô tô", "Cấm các loại xe máy đi vào (ngoại trừ xe máy được ưu tiên theo quy định). Biển không áp dụng với người dắt xe máy.", "p104.png", probihitionSignId);
			trafficSignRepo.save(p104);
			TrafficSign p105 = new TrafficSign("P.105", "Cấm ô tô và mô tô", "Cấm các loại xe cơ giới và xe máy đi vào, ngoại trừ xe gắn máy và xe ưu tiên theo quy định.", "p105.png", probihitionSignId);
			trafficSignRepo.save(p105);
			TrafficSign p106a = new TrafficSign("P.106a", "Cấm ô tô tải", "Cấm xe tải, xe máy kéo, các xe máy chuyên dùng đi vào (trừ xe ưu tiên theo quy định).", "p106a.png", probihitionSignId);
			trafficSignRepo.save(p106a);
			TrafficSign p106b = new TrafficSign("P.106b", "Cấm ô tô tải theo trọng lượng", "Cấm xe tải có khối lượng chuyên chở lớn hơn giá trị ghi trong biển báo đi vào.", "p106b.png", probihitionSignId);
			trafficSignRepo.save(p106b);
			TrafficSign p106c = new TrafficSign("P.106c", "Cấm ô tô tải chở hàng nguy hiểm", "Cấm xe tải chở hàng nguy hiểm đi vào.", "p106c.png", probihitionSignId);
			trafficSignRepo.save(p106c);
			TrafficSign p107 = new TrafficSign("P.107", "Cấm ô tô khách và ô tô tải", "Cấm các loại xe ô tô chở khách, xe tải, xe máy kéo, xe máy thi công chuyên dụng đi vào (trừ xe ưu tiên theo quy định).", "p107.png", probihitionSignId);
			trafficSignRepo.save(p107);
			TrafficSign p107a = new TrafficSign("P.107a", "Cấm ô tô khách", "Cấm các loại ô tô chở khách đi vào (trừ xe ưu tiên theo quy định). Không cấm xe buýt. Trong trường hợp cấm xe khách theo số chỗ ngồi sẽ có biển phụ bên dưới.", "p107a.png", probihitionSignId);
			trafficSignRepo.save(p107a);
			TrafficSign p107b = new TrafficSign("P.107b", "Cấm xe taxi", "Cấm xe taxi đi vào. Trong trường hợp cấm xe taxi theo giờ sẽ có biển phụ bên dưới.", "p107b.png", probihitionSignId);
			trafficSignRepo.save(p107b);
			TrafficSign p108 = new TrafficSign("P.108", "Cấm ô tô kéo rơ-mooc", "Cấm các loại xe cơ giới kéo rơ-mooc, kể cả xe khách – máy kéo – xe máy kéo theo rơ-mooc đi vào, trừ loại ô tô sơ-mi-rơ-mooc và xe ưu tiên theo quy định.", "p108.png", probihitionSignId);
			trafficSignRepo.save(p108);
			TrafficSign p108a = new TrafficSign("P.108a", "Cấm xe sơ-mi-rơ-mooc", "Cấm các loại xe sơ-mi-rơ-mooc, xe kéo rơ-mooc đi vào (trừ xe ưu tiên theo quy định).", "p108a.png", probihitionSignId);
			trafficSignRepo.save(p108a);
			TrafficSign p109 = new TrafficSign("P.109", "Cấm máy kéo", "Cấm các loại máy kéo, máy kéo bánh xích/bánh hơi đi vào.", "p109.png", probihitionSignId);
			trafficSignRepo.save(p109);
			TrafficSign p110a = new TrafficSign("P.110a", "Cấm xe đạp", "Cấm xe đạp đi vào. Không áp dụng cấm người dẫn xe đạp.", "p110a.png", probihitionSignId);
			trafficSignRepo.save(p110a);
			TrafficSign p110b = new TrafficSign("P.110b", "Cấm xe đạp thồ", "Cấm xe đạp thô. Không áp dụng cấm người dẫn xe đạp thô.", "p110b.png", probihitionSignId);
			trafficSignRepo.save(p110b);
			TrafficSign p111a = new TrafficSign("P.111a", "Cấm xe máy", "Cấm các loại xe máy, xe gắn máy đi vào. Không áp dụng cấm người đi xe đạp.", "p111a.png", probihitionSignId);
			trafficSignRepo.save(p111a);
			TrafficSign p111b = new TrafficSign("P.111b", "Cấm xe 3 bánh có động cơ", "Cấm xe 3 bánh có gắn động cơ như xe xích lô, xe lam, xe lôi máy…", "p111b.png", probihitionSignId);
			trafficSignRepo.save(p111b);
			TrafficSign p111c = new TrafficSign("P.111c", "Cấm xe 3 bánh không động cơ", "Cấm xe 3 bánh không gắn động cơ như xe xích lô, xe lôi đạp…", "p111c.png", probihitionSignId);
			trafficSignRepo.save(p111c);
			TrafficSign p112 = new TrafficSign("P.112", "Cấm người đi bộ", "Cấm người đi bộ đi vào.", "p112.png", probihitionSignId);
			trafficSignRepo.save(p112);
			TrafficSign p113 = new TrafficSign("P.113", "Cấm xe người kéo, đẩy", "Cấm xe thô sơ, xe do người đẩy/kéo đi vào. Không áp dụng cấm xe nôi trẻ em, phương tiện chuyên dùng của người khuyết tật.", "p113.png", probihitionSignId);
			trafficSignRepo.save(p113);
			TrafficSign p114 = new TrafficSign("P.114", "Cấm xe súc vật kéo", "Cấm xe sử dụng súc vật kéo hay chở trên lưng đi vào.", "p114.png", probihitionSignId);
			trafficSignRepo.save(p114);
			TrafficSign p115 = new TrafficSign("P.115", "Hạn chế tải trọng toàn bộ xe", "Cấm các loại xe cơ giới/thô sơ (kể cả xe ưu tiên) có tải trọng toàn bộ xe vượt quá trị số ghi trên biển đi vào.", "p115.png", probihitionSignId);
			trafficSignRepo.save(p115);
			TrafficSign p116 = new TrafficSign("P.116", "Hạn chế tải trọng trục xe", "Cấm các loại xe cơ giới/thô sơ (kể cả xe ưu tiên) có tải trọng toàn bộ xe phân bổ trên một trục xe vượt quá trị số ghi trên biển đi vào.", "p116.png", probihitionSignId);
			trafficSignRepo.save(p116);
			TrafficSign p117 = new TrafficSign("P.117", "Hạn chế chiều cao xe", "Cấm các loại xe cơ giới/thô sơ (kể cả xe ưu tiên) có chiều cao vượt quá trị số ghi trên biển đi vào.", "p117.png", probihitionSignId);
			trafficSignRepo.save(p117);
			TrafficSign p118 = new TrafficSign("P.118", "Hạn chế chiều ngang xe", "Cấm các loại xe cơ giới/thô sơ (kể cả xe ưu tiên) có chiều ngang vượt quá trị số ghi trên biển đi vào.", "p118.png", probihitionSignId);
			trafficSignRepo.save(p118);
			TrafficSign p119 = new TrafficSign("P.119", "Hạn chế chiều dài xe", "Cấm các loại xe cơ giới/thô sơ (kể cả xe ưu tiên) có chiều dài vượt quá trị số ghi trên biển đi vào.", "p119.png", probihitionSignId);
			trafficSignRepo.save(p119);
			TrafficSign p120 = new TrafficSign("P.120", "Hạn chế chiều dài xe kéo moóc", "Cấm các loại xe cơ giới kéo mooc, xe sơ-mi-rơ-mooc có chiều dài vượt quá trị số ghi trên biển (kể cả xe ưu tiên) đi vào.", "p120.png", probihitionSignId);
			trafficSignRepo.save(p120);
			TrafficSign p121 = new TrafficSign("P.121", "Cự ly tối thiểu giữa hai xe", "Các xe ô tô phải di chuyển cách nhau một khoảng tối thiểu ghi trên biển.", "p121.png", probihitionSignId);
			trafficSignRepo.save(p121);
			TrafficSign p123a = new TrafficSign("P.123a", "Cấm rẽ trái", "Cấm xe các loại xe cơ giới/thô sơ rẽ trái (trừ xe ưu tiên theo quy định). Không áp dụng cấm quay đầu xe.", "p123a.png", probihitionSignId);
			trafficSignRepo.save(p123a);
			TrafficSign p123b = new TrafficSign("P.123b", "Cấm rẽ phải", "Cấm xe các loại xe cơ giới/thô sơ rẽ phải (trừ xe ưu tiên theo quy định). Không áp dụng cấm quay đầu xe.", "p123b.png", probihitionSignId);
			trafficSignRepo.save(p123b);
			TrafficSign p124a = new TrafficSign("P.124a", "Cấm quay đầu xe", "Cấm các loại xe quay đầu kiểu chữ U theo chiều mũi tên trên biển.", "p124a.png", probihitionSignId);
			trafficSignRepo.save(p124a);
			TrafficSign p124b = new TrafficSign("P.124b", "Cấm xe ô tô quay đầu", "Cấm xe ô tô quay đầu kiểu chữ U theo chiều mũi tên trên biển.", "p124b.png", probihitionSignId);
			trafficSignRepo.save(p124b);
			TrafficSign p124c = new TrafficSign("P.124c", "Cấm rẽ trái và quay đầu xe", "Cấm các loại xe rẽ trái và quay đầu trái theo chiều mũi tên trên biển.", "p124c.png", probihitionSignId);
			trafficSignRepo.save(p124c);
			TrafficSign p124d = new TrafficSign("P.124d", "Cấm rẽ phải và quay đầu xe", "Cấm các loại xe rẽ phải và quay đầu phải theo chiều mũi tên trên biển.", "p124d.png", probihitionSignId);
			trafficSignRepo.save(p124d);
			TrafficSign p124e = new TrafficSign("P.124e", "Cấm xe ô tô rẽ trái và quay đầu xe", "Cấm xe ô tô rẽ trái và quay đầu trái theo chiều mũi tên trên biển.", "p124e.png", probihitionSignId);
			trafficSignRepo.save(p124e);
			TrafficSign p124f = new TrafficSign("P.124f", "Cấm xe ô tô rẽ phải và quay đầu xe", "Cấm xe ô tô rẽ phải và quay đầu phải theo chiều mũi tên trên biển.", "p124f.png", probihitionSignId);
			trafficSignRepo.save(p124f);
			TrafficSign p125 = new TrafficSign("P.125", "Cấm vượt", "Cấm các loại xe cơ giới vượt nhau (kể cả xe ưu tiên theo quy định), nhưng được phép vượt xe máy 2 bánh, xe gắn máy.", "p125.png", probihitionSignId);
			trafficSignRepo.save(p125);
			TrafficSign p126 = new TrafficSign("P.126", "Cấm ô tô tải vượt", "Cấm các loại xe tải vượt xe cơ giới khác, được phép vượt xe máy 2 bánh, xe gắn máy. Không áp dụng các loại xe cơ giới khác vượt xe nhau và vượt xe tải.", "p126.png", probihitionSignId);
			trafficSignRepo.save(p126);
			TrafficSign p127 = new TrafficSign("P.127", "Tốc độ tối đa cho phép", "Các xe cơ giới chạy không vượt quá tốc độ ghi trên biển (trừ xe ưu tiên theo quy định).", "p127.png", probihitionSignId);
			trafficSignRepo.save(p127);
			TrafficSign p127a = new TrafficSign("P.127a", "Tốc độ tối đa cho phép vào ban đêm", "Các xe cơ giới chạy không vượt quá tốc độ ghi trên biển (trừ xe ưu tiên theo quy định) chỉ áp dụng vào ban đêm.", "p127a.png", probihitionSignId);
			trafficSignRepo.save(p127a);
			TrafficSign p127b = new TrafficSign("P.127b", "Tốc độ tối đa trên từng làn đường", "Biển ghép tốc độ tối đa cho phép trên từng làn đường.", "p127b.png", probihitionSignId);
			trafficSignRepo.save(p127b);
			TrafficSign p127c = new TrafficSign("P.127c", "Tốc độ tối đa phương tiện theo từng làn đường", "Biển ghép tốc độ tối đa cho phép theo phương tiện, trên từng làn đường.", "p127c.png", probihitionSignId);
			trafficSignRepo.save(p127c);
			TrafficSign p127d = new TrafficSign("P.127d", "Biển hết hạn chế tốc độ tối đa", "Biển hết hạn chế tốc độ tối đa cho phép theo biển ghép.", "p127d.png", probihitionSignId);
			trafficSignRepo.save(p127d);
			TrafficSign p128 = new TrafficSign("P.128", "Cấm sử dụng còi", "Cấm các loại xe sử dụng còi.", "p128.png", probihitionSignId);
			trafficSignRepo.save(p128);
			TrafficSign p129 = new TrafficSign("P.129", "Kiểm tra", "Báo nơi đặt trạm kiểm tra, các loại xe vận tải đi qua phải dừng lại để kiểm tra theo quy định.", "p129.png", probihitionSignId);
			trafficSignRepo.save(p129);
			TrafficSign p130 = new TrafficSign("P.130", "Cấm dừng xe và đỗ xe", "Cấm các loại xe cơ giới dừng và đỗ xe phía đường có đặt biển (trừ xe ưu tiên theo quy định).", "p130.png", probihitionSignId);
			trafficSignRepo.save(p130);
			TrafficSign p131a = new TrafficSign("P.131a", "Cấm đỗ xe", "Cấm các loại xe cơ giới đỗ xe phía đường có đặt biển (trừ xe ưu tiên).", "p131a.png", probihitionSignId);
			trafficSignRepo.save(p131a);
			TrafficSign p131b = new TrafficSign("P.131b", "Cấm đỗ xe ngày lẻ", "Cấm các loại xe cơ giới đỗ xe phía đường có đặt biển (trừ xe ưu tiên) vào những ngày lẻ.", "p131b.png", probihitionSignId);
			trafficSignRepo.save(p131b);
			TrafficSign p131c = new TrafficSign("P.131c", "Cấm đỗ xe ngày chẵn", "Cấm các loại xe cơ giới đỗ xe phía đường có đặt biển (trừ xe ưu tiên) vào những ngày chẵn.", "p131c.png", probihitionSignId);
			trafficSignRepo.save(p131c);
			TrafficSign p132 = new TrafficSign("P.132", "Nhường đường cho xe cơ giới đi ngược chiều qua đường hẹp", "Các loại xe cơ giới/thô sơ (kể cả xe ưu tiên theo quy định) phải nhường đường cho các loại xe cơ giới đang di chuyển chiều ngược lại.", "p132.png", probihitionSignId);
			trafficSignRepo.save(p132);
			TrafficSign p133 = new TrafficSign("P.133", "Hết cấm vượt", "Biển thông báo hết đoạn đường cấm vượt.", "p133.png", probihitionSignId);
			trafficSignRepo.save(p133);
			TrafficSign p134 = new TrafficSign("P.134", "Hết hạn chế tốc độ tối đa", "Biển thông báo hết đoạn đường hạn chế tốc độ tối đa.", "p134.png", probihitionSignId);
			trafficSignRepo.save(p134);
			TrafficSign p135 = new TrafficSign("P.135", "Hết tất cả các lệnh cấm", "Biển thông báo hết đoạn đường áp dụng tất cả các lệnh cấm.", "p135.png", probihitionSignId);
			trafficSignRepo.save(p135);
			TrafficSign p136 = new TrafficSign("P.136", "Cấm đi thẳng", "Cấm các loại xe cơ giới/thô sơ đi thẳng vào đoạn đường phía trước.", "p136.png", probihitionSignId);
			trafficSignRepo.save(p136);
			TrafficSign p137 = new TrafficSign("P.137", "Cấm rẽ trái và rẽ phải", "Cấm các loại xe cơ giới rẽ trái, rẽ phải. Biển đặt ở những vị trí ngay trước nút giao đường. Trong trường hợp có quy định thời gian cấm sẽ có biển phụ ở dưới.", "p137.png", probihitionSignId);
			trafficSignRepo.save(p137);
			TrafficSign p138 = new TrafficSign("P.138", "Cấm đi thẳng và rẽ trái", "Cấm các loại xe cơ giới đi thẳng, rẽ trái. Biển đặt ở những vị trí ngay trước nút giao đường. Trong trường hợp có quy định thời gian cấm sẽ có biển phụ ở dưới.", "p138.png", probihitionSignId);
			trafficSignRepo.save(p138);
			TrafficSign p139 = new TrafficSign("P.139", "Cấm đi thẳng và rẽ phải", "Cấm các loại xe cơ giới đi thẳng, rẽ phải. Biển đặt ở những vị trí ngay trước nút giao đường. Trong trường hợp có quy định thời gian cấm sẽ có biển phụ ở dưới.", "p139.png", probihitionSignId);
			trafficSignRepo.save(p139);
			TrafficSign p140 = new TrafficSign("P.140", "Cấm xe công nông và các loại xe tương tự", "Cấm các loại xe công nông, xe tương tự công nông đi vào.", "p140.png", probihitionSignId);
			trafficSignRepo.save(p140);
			TrafficSign s508a = new TrafficSign("S.508a", "Biển cấm theo giờ", "Khi cần báo hiệu cấm các loại phương tiện giao thông đường bộ theo giờ trong thành phố, thị xã phải đặt biển phụ số S508(a,b) dưới biển cấm và có chú thích bằng tiếng Việt, phụ đề tiếng Anh trong biển này.", "s508a.png", probihitionSignId);
			trafficSignRepo.save(s508a);
			TrafficSign s508b = new TrafficSign("S.508b", "Biển cấm theo giờ", "Khi cần báo hiệu cấm các loại phương tiện giao thông đường bộ theo giờ trong thành phố, thị xã phải đặt biển phụ số S508(a,b) dưới biển cấm và có chú thích bằng tiếng Việt, phụ đề tiếng Anh trong biển này.", "s508b.png", probihitionSignId);
			trafficSignRepo.save(s508b);
			
			// Biển báo nguy hiểm
			TrafficSign w201a = new TrafficSign("W.201a", "Chỗ ngoặc nguy hiểm bên trái", "Báo trước sắp đến một chỗ ngoặt nguy hiểm phía bên trái.", "w201a.png", dangerSignId);
			trafficSignRepo.save(w201a);
			TrafficSign w201b = new TrafficSign("W.201b", "Chỗ ngoặc nguy hiểm bên phải", "Báo trước sắp đến một chỗ ngoặt nguy hiểm phía bên phải.", "w201b.png", dangerSignId);
			trafficSignRepo.save(w201b);
			TrafficSign w201c = new TrafficSign("W.201c", "Chỗ ngoặt nguy hiểm có nguy cơ lật xe bên phải", "Báo trước chỗ ngoặc nguy hiểm có nguy cơ lật xe bên phải.", "w201c.png", dangerSignId);
			trafficSignRepo.save(w201c);
			TrafficSign w201d = new TrafficSign("W.201d", "Chỗ ngoặt nguy hiểm có nguy cơ lật xe bên trái", "Báo trước chỗ ngoặc nguy hiểm có nguy cơ lật xe bên trái.", "w201d.png", dangerSignId);
			trafficSignRepo.save(w201d);
			TrafficSign w202a = new TrafficSign("W.202a", "Nhiều chỗ ngoặt nguy hiểm liên tiếp", "Báo trước sắp đến nhiều chỗ ngoặt nguy hiểm liên tiếp trong đó chỗ ngoặt đầu tiên hướng vòng bên trái.", "w202a.png", dangerSignId);
			trafficSignRepo.save(w202a);
			TrafficSign w202b = new TrafficSign("W.202b", "Nhiều chỗ ngoặt nguy hiểm liên tiếp", "Báo trước sắp đến nhiều chỗ ngoặt nguy hiểm liên tiếp trong đó chỗ ngoặt đầu tiên hướng vòng bên phải.", "w202b.png", dangerSignId);
			trafficSignRepo.save(w202b);
			TrafficSign w203a = new TrafficSign("W.203a", "Đường bị hẹp cả hai bên", "Báo trước sắp đến một đoạn đường bị hẹp đột ngột cả hai bên.", "w203a.png", dangerSignId);
			trafficSignRepo.save(w203a);
			TrafficSign w203b = new TrafficSign("W.203b", "Đường bị hẹp về phía trái", "Báo trước sắp đến một đoạn đường bị hẹp đột ngột cả hai bên.", "w203b.png", dangerSignId);
			trafficSignRepo.save(w203b);
			TrafficSign w203c = new TrafficSign("W.203c", "Đường bị hẹp về phía phải", "Báo trước sắp đến một đoạn đường bị hẹp đột ngột phía bên phải.", "w203c.png", dangerSignId);
			trafficSignRepo.save(w203c);
			TrafficSign w204 = new TrafficSign("W.204", "Đường hai chiều", "Báo trước sắp đến một đoạn đường do sửa chữa hoặc có trở ngại ở một phía đường mà phải tổ chức đi lại cho phương tiện cả hai chiều trên phía đường còn lại hoặc để báo trước đoạn đường đôi tạm thời hoặc đoạn đường có chiều xe đi và về đi chung.", "w204.png", dangerSignId);
			trafficSignRepo.save(w204);
			TrafficSign w205a = new TrafficSign("W.205a", "Đường giao nhau cùng cấp", "Báo trước sắp đến nơi giao nhau cùng mức của các tuyến đường cùng cấp (không có đường nào ưu tiên)  trên cùng một mặt bằng.", "w205a.png", dangerSignId);
			trafficSignRepo.save(w205a);
			TrafficSign w205b = new TrafficSign("W.205b", "Đường giao nhau cùng cấp", "Báo trước sắp đến nơi giao nhau cùng mức của các tuyến đường cùng cấp (không có đường nào ưu tiên)  trên cùng một mặt bằng.", "w205b.png", dangerSignId);
			trafficSignRepo.save(w205b);
			TrafficSign w205c = new TrafficSign("W.205c", "Đường giao nhau cùng cấp", "Báo trước sắp đến nơi giao nhau cùng mức của các tuyến đường cùng cấp (không có đường nào ưu tiên)  trên cùng một mặt bằng.", "w205c.png", dangerSignId);
			trafficSignRepo.save(w205c);
			TrafficSign w205d = new TrafficSign("W.205d", "Đường giao nhau cùng cấp", "Báo trước sắp đến nơi giao nhau cùng mức của các tuyến đường cùng cấp (không có đường nào ưu tiên)  trên cùng một mặt bằng.", "w205d.png", dangerSignId);
			trafficSignRepo.save(w205d);
			TrafficSign w205e = new TrafficSign("W.205e", "Đường giao nhau cùng cấp", "Báo trước sắp đến nơi giao nhau cùng mức của các tuyến đường cùng cấp (không có đường nào ưu tiên)  trên cùng một mặt bằng.", "w205e.png", dangerSignId);
			trafficSignRepo.save(w205e);
			TrafficSign w206 = new TrafficSign("W.206", "Giao nhau chạy theo vòng xuyến", "Báo trước sắp đến nơi giao nhau có bố trí đảo an toàn ở giữa nút giao, các loại xe qua nút giao phải đi vòng xuyến quanh đảo an toàn theo chiều mũi tên.", "w206.png", dangerSignId);
			trafficSignRepo.save(w206);
			TrafficSign w207a = new TrafficSign("W.207a", "Giao nhau với đường không ưu tiên", "Báo trước sắp đến nơi giao nhau với đường không ưu tiên.", "w207a.png", dangerSignId);
			trafficSignRepo.save(w207a);
			TrafficSign w207b = new TrafficSign("W.207b", "Giao nhau với đường không ưu tiên", "Báo trước sắp đến nơi giao nhau với đường không ưu tiên.", "w207b.png", dangerSignId);
			trafficSignRepo.save(w207b);
			TrafficSign w207c = new TrafficSign("W.207c", "Giao nhau với đường không ưu tiên", "Báo trước sắp đến nơi giao nhau với đường không ưu tiên.", "w207c.png", dangerSignId);
			trafficSignRepo.save(w207c);
			TrafficSign w207d = new TrafficSign("W.207d", "Giao nhau với đường không ưu tiên", "Báo trước sắp đến nơi giao nhau với đường không ưu tiên.", "w207d.png", dangerSignId);
			trafficSignRepo.save(w207d);
			TrafficSign w207e = new TrafficSign("W.207e", "Giao nhau với đường không ưu tiên", "Báo trước sắp đến nơi giao nhau với đường không ưu tiên.", "w207e.png", dangerSignId);
			trafficSignRepo.save(w207e);
			TrafficSign w207f = new TrafficSign("W.207f", "Giao nhau với đường không ưu tiên", "Báo trước sắp đến nơi giao nhau với đường không ưu tiên.", "w207f.png", dangerSignId);
			trafficSignRepo.save(w207f);
			TrafficSign w207g = new TrafficSign("W.207g", "Giao nhau với đường không ưu tiên", "Báo trước sắp đến nơi giao nhau với đường không ưu tiên.", "w207g.png", dangerSignId);
			trafficSignRepo.save(w207g);
			TrafficSign w207h = new TrafficSign("W.207h", "Giao nhau với đường không ưu tiên", "Báo trước sắp đến nơi giao nhau với đường không ưu tiên.", "w207h.png", dangerSignId);
			trafficSignRepo.save(w207h);
			TrafficSign w207i = new TrafficSign("W.207i", "Giao nhau với đường không ưu tiên", "Báo trước sắp đến nơi giao nhau với đường không ưu tiên.", "w207i.png", dangerSignId);
			trafficSignRepo.save(w207i);
			TrafficSign w207k = new TrafficSign("W.207k", "Giao nhau với đường không ưu tiên", "Báo trước sắp đến nơi giao nhau với đường không ưu tiên.", "w207k.png", dangerSignId);
			trafficSignRepo.save(w207k);
			TrafficSign w208 = new TrafficSign("W.208", "Giao nhau với đường ưu tiên", "Báo trước sắp đến nơi giao nhau với đường ưu tiên.", "w208.png", dangerSignId);
			trafficSignRepo.save(w208);
			TrafficSign w209 = new TrafficSign("W.209", "Giao nhau có tín hiệu đèn", "Báo trước nơi giao nhau có điều khiển giao thông bằng tín hiệu đèn trong trường hợp người lái xe khó quan sát để kịp thời xử lý.", "w209.png", dangerSignId);
			trafficSignRepo.save(w209);
			TrafficSign w210 = new TrafficSign("W.210", "Giao nhau với đường sắt có rào chắn", "Báo trước sắp đến chỗ giao nhau giữa đường bộ và đường sắt có rào chắn kín hay rào chắn nửa kín và có nhân viên ngành đường sắt điều khiển giao thông.", "w210.png", dangerSignId);
			trafficSignRepo.save(w210);
			TrafficSign w211a = new TrafficSign("W.211a", "Giao nhau với đường sắt không có rào chắn", "Báo trước sắp đến chỗ giao nhau giữa đường bộ và đường sắt không có rào chắn, không có người điều khiển giao thông.", "w211a.png", dangerSignId);
			trafficSignRepo.save(w211a);
			TrafficSign w211b = new TrafficSign("W.211b", "Giao nhau với đường tàu điện", "Chỉ nơi đường bộ giao nhau cùng mức với đường tàu điện.", "w211b.png", dangerSignId);
			trafficSignRepo.save(w211b);
			TrafficSign w212 = new TrafficSign("W.212", "Cầu hẹp", "Báo trước sắp đến cầu hẹp là loại cầu có chiều rộng phần xe chạy nhỏ hơn hoặc bằng 4,5m.", "w212.png", dangerSignId);
			trafficSignRepo.save(w212);
			TrafficSign w213 = new TrafficSign("W.213", "Cầu tạm", "Báo trước sắp đến cầu tạm là loại cầu được làm để sử dụng tạm thời cho xe cộ qua lại.", "w213.png", dangerSignId);
			trafficSignRepo.save(w213);
			TrafficSign w214 = new TrafficSign("W.214", "Cầu quay-cầu cất", "Báo trước sắp đến cầu xoay, cầu cất là loại cầu trong từng thời gian có cắt giao thông đường bộ bằng cách quay hoặc nâng nhịp thông thuyền để cho tàu thuyền qua lại.", "w214.png", dangerSignId);
			trafficSignRepo.save(w214);
			TrafficSign w215 = new TrafficSign("W.215", "Kè, vực sâu phía trước", "Báo trước sắp đến những vị trí có kè chắn vực sâu, hoặc sông suối đi sát đường, cần đề phòng tình huống nguy hiểm rơi xuống vực sâu hoặc sông su ối (thường có ở những chỗ ngoặt nguy hiểm).", "w215.png", dangerSignId);
			trafficSignRepo.save(w215);
			TrafficSign w216 = new TrafficSign("W.216", "Đường ngầm", "Báo trước sắp đến những vị trí có đường ngầm (đường tràn) phải đặt biển số 216 \"Đường ngầm\".", "w216.png", dangerSignId);
			trafficSignRepo.save(w216);
			TrafficSign w217 = new TrafficSign("W.217", "Bến phà", "Báo trước sắp đến bến phà.", "w217.png", dangerSignId);
			trafficSignRepo.save(w217);
			TrafficSign w218 = new TrafficSign("W.218", "Cửa chui", "Báo trước sắp đến đường có cổng chắn ngang, kiểu cổng như đường hầm, cổng thành, cầu vượt đường bộ dạng cầu vòm v.v....", "w218.png", dangerSignId);
			trafficSignRepo.save(w218);
			TrafficSign w219 = new TrafficSign("W.219", "Dốc xuống nguy hiểm", "Báo trước sắp đến đoạn đường xuống dốc nguy hiểm.", "w219.png", dangerSignId);
			trafficSignRepo.save(w219);
			TrafficSign w220 = new TrafficSign("W.220", "Dốc lên nguy hiểm", "Báo trước sắp đến đoạn đường lên dốc nguy hiểm.", "w220.png", dangerSignId);
			trafficSignRepo.save(w220);
			TrafficSign w221a = new TrafficSign("W.221a", "Đường có ổ gà, lồi lõm", "Đặt trong trường hợp đường đang tốt, xe chạy nhanh, chuyển sang những đoạn lồi lõm, gập ghềnh, ổ gà, lượn sóng.", "w221a.png", dangerSignId);
			trafficSignRepo.save(w221a);
			TrafficSign w221b = new TrafficSign("W.221b", "Đường có sóng mấp mô nhân tạo", "Để hạn chế tốc độ xe chạy (biển được cắm kèm theo biển số 227 \"Hạn chế tốc độ tối đa\"), bắt buộc lái xe phải chạy với tốc độ chậm trước khi qua những điểm cần kiểm soát, kiểm tra hoặc giảm tốc độ trước khi vào đoạn đường hạn chế tốc độ tối đa.", "w221b.png", dangerSignId);
			trafficSignRepo.save(w221b);
			TrafficSign w222a = new TrafficSign("W.222a", "Đường trơn", "Báo trước sắp tới đoạn đường có thể xảy ra trơn trượt đặc biệt là khi thời tiết xấu, mưa phùn (hệ số bám của lốp với mặt đường < 0,3) cần tránh hãm phanh, tăng ga, sang số đột ngột hoặc xe chạy với tốc độ cao sẽ bị nguy hiểm.", "w222a.png", dangerSignId);
			trafficSignRepo.save(w222a);
			TrafficSign w222b = new TrafficSign("W.222b", "Lề đường nguy hiểm", "Báo những nơi lề đường không ổn định, khi xe đi vào dễ gây văng đất đá hoặc bánh xe quay tại chỗ.", "w222b.png", dangerSignId);
			trafficSignRepo.save(w222b);
			TrafficSign w223a = new TrafficSign("W.223a", "Vách núi nguy hiểm", "Báo hiệu sắp đi vào đoạn đường đi sát vách núi nằm ở bên tay trái, đường vừa hẹp vừa hạn chế tầm nhìn, người lái xe phải cẩn thận.", "w223a.png", dangerSignId);
			trafficSignRepo.save(w223a);
			TrafficSign w223b = new TrafficSign("W.223b", "Vách núi nguy hiểm", "Báo hiệu sắp đi vào đoạn đường đi sát vách núi nằm ở bên tay phải, đường vừa hẹp vừa hạn chế tầm nhìn, người lái xe phải cẩn thận.", "w223b.png", dangerSignId);
			trafficSignRepo.save(w223b);
			TrafficSign w224 = new TrafficSign("W.224", "Đường người đi bộ cắt ngang", "Báo trước sắp tới phần đường dành cho người đi bộ sang qua đường. Gặp biển này các xe phải giảm tốc độ, nhường ưu tiên cho người đi bộ và chỉ được chạy xe khi không gây nguy hiểm cho người đi bộ.", "w224.png", dangerSignId);
			trafficSignRepo.save(w224);
			TrafficSign w225 = new TrafficSign("W.225", "Trẻ em", "Báo trước là gần đến đoạn đường thường có trẻ em đi ngang qua hoặc tụ tập trên đường như ở vườn trẻ, trường học, câu lạc bộ.", "w225.png", dangerSignId);
			trafficSignRepo.save(w225);
			TrafficSign w226 = new TrafficSign("W.226", "Đường người đi xe đạp cắt ngang", "Báo trước là gần tới vị trí thường có người đi xe đạp từ những đường nhỏ cắt ngang qua hoặc từ đường dành cho xe đạp đi nhập vào đường ôtô.", "w226.png", dangerSignId);
			trafficSignRepo.save(w226);
			TrafficSign w227 = new TrafficSign("W.227", "Công trường", "Báo trước gần tới đoạn đường đang tiến hành thi công sửa chữa, cải tạo, nâng cấp có người và máy móc đang làm việc trên mặt đường. Khi gặp biển báo này tốc độ xe chạy phải giảm cho thích hợp, không gây nguy hiểm cho người và máy móc trên đoạn đường đó.", "w227.png", dangerSignId);
			trafficSignRepo.save(w227);
			TrafficSign w228a = new TrafficSign("W.228a", "Đá lở", "Báo trước gần tới đoạn đường có hiện tượng đất đá từ trên ta luy dương sụt lở bất ngờ gây nguy hiểm cho xe cộ và người đi đường, đặc biệt là ở những đoạn đường miền núi. Gặp biển này, người lái xe phải chú ý; đặc biệt khi thời tiết xấu, hạn chế tầm nhìn, không dừng hay đỗ xe trong khu vực đá lở sau những trận mưa lớn.", "w228a.png", dangerSignId);
			trafficSignRepo.save(w228a);
			TrafficSign w228b = new TrafficSign("W.228b", "Đá lở", "Báo trước gần tới đoạn đường có hiện tượng đất đá từ trên ta luy dương sụt lở bất ngờ gây nguy hiểm cho xe cộ và người đi đường, đặc biệt là ở những đoạn đường miền núi. Gặp biển này, người lái xe phải chú ý; đặc biệt khi thời tiết xấu, hạn chế tầm nhìn, không dừng hay đỗ xe trong khu vực đá lở sau những trận mưa lớn.", "w228b.png", dangerSignId);
			trafficSignRepo.save(w228b);
			TrafficSign w228c = new TrafficSign("W.228c", "Sỏi đá bắn lên", "Báo trước nơi có kết cấu mặt đường rời rạc, khi phương tiện đi qua, làm cho các viên đá, sỏi băng lên gây nguy hiểm và mất an toàn cho người và phương tiện tham gia giao thông.", "w228c.png", dangerSignId);
			trafficSignRepo.save(w228c);
			TrafficSign w229 = new TrafficSign("W.229", "Giải máy bay lên xuống", "Báo trước đoạn đường ở vùng sát đường băng sân bay và cắt ngang qua hướng máy bay lên xuống ở độ cao không lớn.", "w229.png", dangerSignId);
			trafficSignRepo.save(w229);
			TrafficSign w230 = new TrafficSign("W.230", "Gia súc", "Báo trước gần tới đoạn đường thường có gia súc thả rông hoặc lùa qua ngang đường, đường ở vùng đồng cỏ của nông trường chăn nuôi, vùng thảo nguyên... Người lái xe có trách nhiệm đi chậm, quan sát và dừng lại bảo đảm cho gia súc có thể qua đường không bị nguy hiểm.", "w230.png", dangerSignId);
			trafficSignRepo.save(w230);
			TrafficSign w231 = new TrafficSign("W.231", "Thú rừng vượt qua đường", "Báo trước gần tới đoạn đường thường có thú rừng qua đường như đường đi qua rừng hay khu vực bảo tồn thiên nhiên cấm săn bắn. Người lái xe phải đi chậm, chú ý quan sát hai bên đường và thận trọng đề phòng tai nạn.", "w231.png", dangerSignId);
			trafficSignRepo.save(w231);
			TrafficSign w232 = new TrafficSign("W.232", "Gió ngang", "Báo trước gần tới đoạn đường thường có gió ngang thổi mạnh gây nguy hiểm. Người lái xe cần phải điều chỉnh tốc độ xe chạy cho thích hợp, đề phòng gió thổi mạnh gây lật xe.", "w232.png", dangerSignId);
			trafficSignRepo.save(w232);
			TrafficSign w233 = new TrafficSign("W.233", "Nguy hiểm khác", "Báo trên đường có những nguy hiểm mà không thể vận dụng được các kiểu biển từ biển số 201a đến biển số 232.", "w233.png", dangerSignId);
			trafficSignRepo.save(w233);
			TrafficSign w234 = new TrafficSign("W.234", "Giao nhau với đường hai chiều", "Đặt trên đường một chiều, để báo trước sắp đến vị trí giao nhau với đường hai chiều.", "w234.png", dangerSignId);
			trafficSignRepo.save(w234);
			TrafficSign w235 = new TrafficSign("W.235", "Đường đôi", "Báo trước sắp đến đoạn đường có chiều đi và chiều về phân biệt bằng giải phân cách cứng.", "w235.png", dangerSignId);
			trafficSignRepo.save(w235);
			TrafficSign w236 = new TrafficSign("W.236", "Hết đường đôi", "Báo trước sắp kết thúc đoạn đường có chiều đi và chiều về phân biệt bằng giải phân cách cứng.", "w236.png", dangerSignId);
			trafficSignRepo.save(w236);
			TrafficSign w237 = new TrafficSign("W.237", "Cầu vồng", "Đặt ở trên đoạn đường sắp đến công trình có độ vồng lớn ảnh hưởng tới tầm nhìn, nhắc nhở lái xe lái cẩn thận.", "w237.png", dangerSignId);
			trafficSignRepo.save(w237);
			TrafficSign w238 = new TrafficSign("W.238", "Đường cao tốc phía trước", "Được đặt trên đường nhánh nhập vào đường cao tốc để báo cho các phương tiện đi trên đường này biết có Đường cao tốc ở phía trước.", "w238.png", dangerSignId);
			trafficSignRepo.save(w238);
			TrafficSign w239 = new TrafficSign("W.239", "Đường cáp điện ở phía trên", "Được đặt Ở những nơi có đường dây điện cắt ngang phía trên tuyến đường.", "w239.png", dangerSignId);
			trafficSignRepo.save(w239);
			TrafficSign w240 = new TrafficSign("W.240", "Đường hầm", "Nhắc lái xe chú ý chuẩn bị đi vào hầm đường bộ. Biển đặt ở bên phải chiều đi trước khi vào hầm.", "w240.png", dangerSignId);
			trafficSignRepo.save(w240);
			TrafficSign w241 = new TrafficSign("W.241", "Ùn tắc giao thông", "Báo đoạn đường hay xảy ra ùn tắc giao thông.", "w241.png", dangerSignId);
			trafficSignRepo.save(w241);
			TrafficSign w242a = new TrafficSign("W.242a", "Nơi đường sắt giao vuông góc với đường bộ", "Bổ sung cho biển số 211 \"Giao nhau với đường sắt không có rào chắn\", để chỉ chỗ đường sắt giao vuông góc đường bộ, và tại chỗ giao nhau đường sắt chỉ có một đường cắt ngang đường bộ.", "w242a.png", dangerSignId);
			trafficSignRepo.save(w242a);
			TrafficSign w242b = new TrafficSign("W.242b", "Nơi đường sắt giao vuông góc với đường bộ", "Bổ sung cho biển số 211 \"Giao nhau với đường sắt không có rào chắn\", để chỉ chỗ đường sắt giao vuông góc đường bộ, và tại chỗ giao nhau đường sắt có từ hai đường cắt ngang đường bộ.", "w242b.png", dangerSignId);
			trafficSignRepo.save(w242b);
			TrafficSign w243a = new TrafficSign("W.243a", "Nơi đường sắt giao không vuông góc với đường bộ", "Đặt ở nơi cách ray ngoài cùng nơi giao đường sắt 50m, báo trước sắp đến vị trí giao cắt đường bộ với đường sắt cùng mức, không vuông góc và không có người gác, không có rào chắn.", "w243a.png", dangerSignId);
			trafficSignRepo.save(w243a);
			TrafficSign w243b = new TrafficSign("W.243b", "Nơi đường sắt giao không vuông góc với đường bộ", "Đặt ở nơi cách ray ngoài cùng nơi giao đường sắt 100m, báo trước sắp đến vị trí giao cắt đường bộ với đường sắt cùng mức, không vuông góc và không có người gác, không có rào chắn.", "w243b.png", dangerSignId);
			trafficSignRepo.save(w243b);
			TrafficSign w243c = new TrafficSign("W.243c", "Nơi đường sắt giao không vuông góc với đường bộ", "Đặt ở nơi cách ray ngoài cùng nơi giao đường sắt 150m, báo trước sắp đến vị trí giao cắt đường bộ với đường sắt cùng mức, không vuông góc và không có người gác, không có rào chắn.", "w243c.png", dangerSignId);
			trafficSignRepo.save(w243c);
			TrafficSign w244 = new TrafficSign("W.244", "Đoạn đường hay xảy ra tai nạn", "Cảnh báo nguy hiểm đoạn đường phía trước thường xảy ra tai nạn để lái xe cần đặc biệt chú ý.", "w244.png", dangerSignId);
			trafficSignRepo.save(w244);
			TrafficSign w245a = new TrafficSign("W.245a", "Đi chậm", "Nhắc lái xe giảm tốc độ đi chậm.", "w245a.png", dangerSignId);
			trafficSignRepo.save(w245a);
			TrafficSign w245b = new TrafficSign("W.245b", "Đi chậm", "Nhắc lái xe giảm tốc độ đi chậm, đối với các tuyến đường đối ngoại.", "w245b.png", dangerSignId);
			trafficSignRepo.save(w245b);
			TrafficSign w246a = new TrafficSign("W.246a", "Chú ý chướng ngại vật - Vòng tránh ra hai bên", "Báo trước cho lái xe biết phía trước có chướng ngại vật, xe cần giảm tốc độ và đi vòng tránh ra hai bên.", "w246a.png", dangerSignId);
			trafficSignRepo.save(w246a);
			TrafficSign w246b = new TrafficSign("W.246b", "Chú ý chướng ngại vật - Vòng tránh sang bên trái", "Báo trước cho lái xe biết phía trước có chướng ngại vật, xe cần giảm tốc độ và đi vòng tránh sang bên trái.", "w246b.png", dangerSignId);
			trafficSignRepo.save(w246b);
			TrafficSign w246c = new TrafficSign("W.246c", "Chú ý chướng ngại vật - Vòng tránh sang bên phải", "Báo trước cho lái xe biết phía trước có chướng ngại vật, xe cần giảm tốc độ và đi vòng tránh sang bên phải.", "w246c.png", dangerSignId);
			trafficSignRepo.save(w246c);
			TrafficSign w247 = new TrafficSign("W.247", "Chú ý xe đỗ", "Cảnh báo có các loại xe ô tô; máy kéo; rơ moóc hoặc sơ mi rơ moóc được kéo bởi xe ô tô hoặc ôtô đầu kéo; xe máy chuyên dùng đang đỗ chiếm một phần đường xe chạy (biển này tương tự, chỉ lộn ngược đầu so với biển báo nguy hiểm số 208 \"Giao nhau với đường ưu tiên\").", "w247.png", dangerSignId);
			trafficSignRepo.save(w247);
			
			// Biển báo hiệu lệnh
			TrafficSign r301a = new TrafficSign("R.301a", "Hướng đi phải theo", "Các xe chỉ được đi thẳng (trừ xe được quyền ưu tiên theo quy định).", "r301a.png", commandSignId);
			trafficSignRepo.save(r301a);
			TrafficSign r301b = new TrafficSign("R.301b", "Hướng đi phải theo", "Các xe chỉ được rẽ phải (trừ xe được quyền ưu tiên theo quy định). Biển này được đặt ở sau  nơi đường giao nhau.", "r301b.png", commandSignId);
			trafficSignRepo.save(r301b);
			TrafficSign r301c = new TrafficSign("R.301c", "Hướng đi phải theo", "Các xe chỉ được rẽ trái (trừ xe được quyền ưu tiên theo quy định). Biển này được đặt ở sau nơi đường giao nhau.", "r301c.png", commandSignId);
			trafficSignRepo.save(r301c);
			TrafficSign r301d = new TrafficSign("R.301d", "Hướng đi phải theo", "Các xe chỉ được rẽ phải (trừ xe được quyền ưu tiên theo quy định). Biển này được đặt ở trước nơi đường giao nhau, có tác dụng tại nơi giao nhau đằng sau mặt biển.", "r301d.png", commandSignId);
			trafficSignRepo.save(r301d);
			TrafficSign r301e = new TrafficSign("R.301e", "Hướng đi phải theo", "Các xe chỉ được rẽ trái (trừ xe được quyền ưu tiên theo quy định).  Biển này được đặt ở trước nơi đường giao nhau, có tác dụng tại nơi giao nhau đằng sau mặt biển.", "r301e.png", commandSignId);
			trafficSignRepo.save(r301e);
			TrafficSign r301f = new TrafficSign("R.301f", "Hướng đi phải theo", "Các xe chỉ được đi thẳng và rẽ phải (trừ xe được quyền ưu tiên theo quy định).", "r301f.png", commandSignId);
			trafficSignRepo.save(r301f);
			TrafficSign r301h = new TrafficSign("R.301h", "Hướng đi phải theo", "Các xe chỉ được đi thẳng và rẽ trái (trừ xe được quyền ưu tiên theo quy định).", "r301h.png", commandSignId);
			trafficSignRepo.save(r301h);
			TrafficSign r301i = new TrafficSign("R.301i", "Hướng đi phải theo", "Các xe chỉ được rẽ phải và rẽ trái (trừ xe được quyền ưu tiên theo quy định).", "r301i.png", commandSignId);
			trafficSignRepo.save(r301i);
			TrafficSign r302a = new TrafficSign("R.302a", "Hướng phải đi vòng chướng ngại vật", "Báo các loại xe (cơ giới và thô sơ) hướng đi vòng sang phải để qua một chướng ngại vật.", "r302a.png", commandSignId);
			trafficSignRepo.save(r302a);
			TrafficSign r302b = new TrafficSign("R.302b", "Hướng phải đi vòng chướng ngại vật", "Báo các loại xe (cơ giới và thô sơ) hướng đi vòng sang trái để qua một chướng ngại vật.", "r302b.png", commandSignId);
			trafficSignRepo.save(r302b);
			TrafficSign r303 = new TrafficSign("R.303", "Nơi giao nhau chạy theo vòng xuyến", "Báo cho các loại xe (thô sơ và cơ giới) phải chạy vòng theo đảo an toàn ở nơi đường giao nhau.", "r303.png", commandSignId);
			trafficSignRepo.save(r303);
			TrafficSign r304 = new TrafficSign("R.304", "Đường dành cho xe thô sơ", "Báo đường dành cho xe thô sơ (kể cả xe của người tàn tật) và người đi bộ. Biển bắt buộc các  loại xe thô sơ (kể cả xe của người tàn tật) và người đi bộ phải dùng đường dành riêng này để đi và cấm các xe cơ giới kể cả xe gắn máy, các xe được ưu tiên theo quy định đi vào đường đã đặt biển này.", "r304.png", commandSignId);
			trafficSignRepo.save(r304);
			TrafficSign r305 = new TrafficSign("R.305", "Đường dành cho người đi bộ", "Báo đường dành cho người đi bộ. Các loại xe cơ giới và thô sơ kể cả các xe được ưu tiên theo quy định không được phép đi vào đường đã đặt biển này, trừ trường hợp đi cắt ngang qua nhưng phải đảm bảo tuyệt đối an toàn cho người đi bộ.", "r305.png", commandSignId);
			trafficSignRepo.save(r305);
			TrafficSign r306 = new TrafficSign("R.306", "Tốc độ tối thiểu cho phép", "Báo tốc độ tối thiểu cho phép các xe cơ giới chạy. Biển cấm các loại xe cơ giới chạy với tốc độ nhỏ hơn trị số ghi trên biển.", "r306.png", commandSignId);
			trafficSignRepo.save(r306);
			TrafficSign r307 = new TrafficSign("R.307", "Hết hạn chế tốc độ tối thiểu", "Báo hết đoạn đường hạn chế tốc độ tối thiểu. Kể từ biển này các xe được phép chạy chậm hơn trị số ghi trên biển nhưng không được gây cản trở các xe khác.", "r307.png", commandSignId);
			trafficSignRepo.save(r307);
			TrafficSign r308a = new TrafficSign("R.308a", "Tuyến đường cầu vượt cắt qua", "Biểu thị phía trước có cầu vượt, xe có thể đi thẳng hoặc theo chỉ dẫn trên hình vẽ để rẽ trái.", "r308a.png", commandSignId);
			trafficSignRepo.save(r308a);
			TrafficSign r308b = new TrafficSign("R.308b", "Tuyến đường cầu vượt cắt qua", "Biểu thị phía trước có cầu vượt, xe có thể đi thẳng hoặc theo chỉ dẫn trên hình vẽ để rẽ phải.", "r308b.png", commandSignId);
			trafficSignRepo.save(r308b);
			TrafficSign r309 = new TrafficSign("R.309", "Ấn còi", "Biểu thị xe cộ đi đến vị trí cắm biển đó thì phải ấn còi.", "r309.png", commandSignId);
			trafficSignRepo.save(r309);
			TrafficSign r310a = new TrafficSign("R.310a", "Hướng đi phải theo cho các xe chở hàng nguy hiểm", "báo cho các loại xe chở hàng nguy hiểm phải đi theo hướng quy định (rẽ trái).", "r310a.png", commandSignId);
			trafficSignRepo.save(r310a);
			TrafficSign r310b = new TrafficSign("R.310b", "Hướng đi phải theo cho các xe chở hàng nguy hiểm", "báo cho các loại xe chở hàng nguy hiểm phải đi theo hướng quy định (đi thẳng).", "r310b.png", commandSignId);
			trafficSignRepo.save(r310b);
			TrafficSign r310c = new TrafficSign("R.310c", "Hướng đi phải theo cho các xe chở hàng nguy hiểm", "báo cho các loại xe chở hàng nguy hiểm phải đi theo hướng quy định (rẽ phải).", "r310c.png", commandSignId);
			trafficSignRepo.save(r310c);
			
			// Biển báo chỉ dẫn
			TrafficSign i401 = new TrafficSign("I.401", "Bắt đầu đường ưu tiên", "Để biểu thị ưu tiên cho các phương tiện trên đường có đặt biển này được đi trước. Biển đặt tại vị trí thích hợp trước khi đường nhánh sắp nhập vào trục đường chính, yêu cầu phương tiện từ đường nhánh ra phải dừng lại nhường cho phương tiện trên đường chính đi trước.", "i401.png", directionSignId);
			trafficSignRepo.save(i401);
			TrafficSign i402 = new TrafficSign("I.402", "Hết đoạn đường ưu tiên", "Báo hiệu hết đoạn đường được ưu tiên.", "i402.png", directionSignId);
			trafficSignRepo.save(i402);
			TrafficSign i403a = new TrafficSign("I.403a", "Đường dành cho ôtô", "Để chỉ dẫn bắt đầu đường dành cho các loại ôtô đi lại, các loại phương tiện giao thông khác không được phép đi vào đoạn đường có dặt biển này.", "i403a.png", directionSignId);
			trafficSignRepo.save(i403a);
			TrafficSign i403b = new TrafficSign("I.403b", "Đường dành cho ô tô, xe máy", "Để chỉ dẫn bắt đầu đường dành cho các loại ôtô, xe máy (kể cả xe gắn máy) đi lại, các loại phương tiện giao thông khác không được phép đi vào đoạn đường có đặt biển này.", "i403b.png", directionSignId);
			trafficSignRepo.save(i403b);
			TrafficSign i404a = new TrafficSign("I.404a", "Hết đường dành cho ô tô", "Để chỉ dẫn hết đoạn đường dành cho ôtô đi lại.", "i404a.png", directionSignId);
			trafficSignRepo.save(i404a);
			TrafficSign i404b = new TrafficSign("I.404b", "Hết đường dành cho ô tô, xe máy", "Để chỉ dẫn hết đoạn đường dành cho ôtô, xe máy đi lại.", "i404b.png", directionSignId);
			trafficSignRepo.save(i404b);
			TrafficSign i405a = new TrafficSign("I.405a", "Đường cụt", "Để chỉ dẫn đường cụt, lối rẽ vào đường cụt phía bên phải.", "i405a.png", directionSignId);
			trafficSignRepo.save(i405a);
			TrafficSign i405b = new TrafficSign("I.405b", "Đường cụt", "Để chỉ dẫn đường cụt, lối rẽ vào đường cụt phía bên trái.", "i405b.png", directionSignId);
			trafficSignRepo.save(i405b);
			TrafficSign i405c = new TrafficSign("I.405c", "Đường cụt", "Để chỉ dẫn phía trước là đường cụt, đặt trước đường cụt 300m đến 500m và cứ 100m phải đặt thêm một biển.", "i405c.png", directionSignId);
			trafficSignRepo.save(i405c);
			TrafficSign i406 = new TrafficSign("I.406", "Được ưu tiên qua đường hẹp", "Để chỉ dẫn cho người lái xe cơ giới biết mình được quyền ưu tiên đi trước trên đoạn đường hẹp. Nếu trên hướng đi ngược chiều có xe (cơ giới hoặc thô sơ) đã đi vào phạm vi đường hẹp thì xe đi theo chiều ưu tiên cũng phải nhường đường.", "i406.png", directionSignId);
			trafficSignRepo.save(i406);
			TrafficSign i407a = new TrafficSign("I.407a", "Đường một chiều", "Để chỉ dẫn những đoạn đường chạy một chiều. Biển số 407a đặt sau nơi đường giao nhau, biển số 407b,c đặt trước nơi đường giao nhau.", "i407a.png", directionSignId);
			trafficSignRepo.save(i407a);
			TrafficSign i407b = new TrafficSign("I.407b", "Đường một chiều", "Để chỉ dẫn những đoạn đường chạy một chiều. Biển số 407a đặt sau nơi đường giao nhau, biển số 407b,c đặt trước nơi đường giao nhau.", "i407b.png", directionSignId);
			trafficSignRepo.save(i407b);
			TrafficSign i407c = new TrafficSign("I.407c", "Đường một chiều", "Để chỉ dẫn những đoạn đường chạy một chiều. Biển số 407a đặt sau nơi đường giao nhau, biển số 407b,c đặt trước nơi đường giao nhau.", "i407c.png", directionSignId);
			trafficSignRepo.save(i407c);
			TrafficSign i408 = new TrafficSign("I.408", "Nơi đỗ xe", "Để chỉ dẫn những nơi được phép đỗ xe, những bãi đỗ xe, bến xe v.v...", "i408.png", directionSignId);
			trafficSignRepo.save(i408);
			TrafficSign i409 = new TrafficSign("I.409", "Chỗ quay xe", "Để chỉ dẫn vị trí được phép quay đầu xe. Biển không cho phép rẽ trái (trừ các xe được quyền ưu tiên theo quy định).", "i409.png", directionSignId);
			trafficSignRepo.save(i409);
			TrafficSign i410 = new TrafficSign("I.410", "Khu vực quay xe", "Để chỉ dẫn khu vực được phép quay đầu xe , phải đặt biển số 410 \"Khu vực quay xe\". Trên biển mô tả cách thức tiến hành quay xe. Biển không cho phép rẽ trái (trừ các xe được quyền ưu tiên theo quy định).", "i410.png", directionSignId);
			trafficSignRepo.save(i410);
			TrafficSign i411 = new TrafficSign("I.411", "Hướng đi trên mỗi làn đường theo vạch kẻ đường", "Để chỉ dẫn cho người lái xe biết số lượng làn đường trên mặt đường và hướng đi trên mỗi làn đường theo vạch kẻ đường. Biển sử dụng phối hợp với vạch kẻ đường (loại vạch 1.18 hình mũi tên màu trắng trên mặt đường). Tùy theo tình hình thực tế về số lượng làn đường và hướng đi trên mỗi làn đường mà có ký hiệu chỉ dẫn phù hợp. Biển có tác dụng bắt buộc người lái xe phải đi đúng làn đường đã được chỉ dẫn hướng phù hợp với hành trình cuả xe.", "i411.png", directionSignId);
			trafficSignRepo.save(i411);
			TrafficSign i412a = new TrafficSign("I.412a", "Làn đường dành cho ôtô khách", "Để chỉ dẫn cho người lái xe biết có làn đường dành riêng cho từng loại xe riêng biệt. Biển được đặt phía trên làn xe, ở đầu đường theo chiều xe chạy. Tùy loại phương tiện cần quy định mà bố trí biển cho phù hợp. Các loại xe khác không được đi vào làn đường có đặt biển này (trừ các xe được quyền ưu tiên theo quy định).", "i412a.png", directionSignId);
			trafficSignRepo.save(i412a);
			TrafficSign i412b = new TrafficSign("I.412b", "Làn đường dành cho ôtô con", "Để chỉ dẫn cho người lái xe biết có làn đường dành riêng cho từng loại xe riêng biệt. Biển được đặt phía trên làn xe, ở đầu đường theo chiều xe chạy. Tùy loại phương tiện cần quy định mà bố trí biển cho phù hợp. Các loại xe khác không được đi vào làn đường có đặt biển này (trừ các xe được quyền ưu tiên theo quy định).", "i412b.png", directionSignId);
			trafficSignRepo.save(i412b);
			TrafficSign i412c = new TrafficSign("I.412c", "Làn đường dành cho ôtô tải", "Để chỉ dẫn cho người lái xe biết có làn đường dành riêng cho từng loại xe riêng biệt. Biển được đặt phía trên làn xe, ở đầu đường theo chiều xe chạy. Tùy loại phương tiện cần quy định mà bố trí biển cho phù hợp. Các loại xe khác không được đi vào làn đường có đặt biển này (trừ các xe được quyền ưu tiên theo quy định).", "i412c.png", directionSignId);
			trafficSignRepo.save(i412c);
			TrafficSign i412d = new TrafficSign("I.412d", "Làn đường dành cho xe môtô", "Để chỉ dẫn cho người lái xe biết có làn đường dành riêng cho từng loại xe riêng biệt. Biển được đặt phía trên làn xe, ở đầu đường theo chiều xe chạy. Tùy loại phương tiện cần quy định mà bố trí biển cho phù hợp. Các loại xe khác không được đi vào làn đường có đặt biển này (trừ các xe được quyền ưu tiên theo quy định).", "i412d.png", directionSignId);
			trafficSignRepo.save(i412d);
			TrafficSign i413a = new TrafficSign("I.413a", "Đường có làn đường dành cho ô tô khách", "Để chỉ dẫn cho người lái xe biết đường có làn đường dành riêng cho ôtô khách theo chiều ngược lại.", "i413a.png", directionSignId);
			trafficSignRepo.save(i413a);
			TrafficSign i413b = new TrafficSign("I.413b", "Rẽ ra đường có làn đường dành cho ô tô khách", "Để chỉ dẫn cho người lái xe biết ở nơi đường giao nhau rẽ phải là rẽ ra đường có làn đường dành cho ôtô khách.", "i413b.png", directionSignId);
			trafficSignRepo.save(i413b);
			TrafficSign i413c = new TrafficSign("I.413c", "Rẽ ra đường có làn đường dành cho ô tô khách", "Để chỉ dẫn cho người lái xe biết ở nơi đường giao nhau rẽ trái là rẽ ra đường có làn đường dành cho ôtô khách.", "i413c.png", directionSignId);
			trafficSignRepo.save(i413c);
			TrafficSign i415 = new TrafficSign("I.415", "Mũi tên chỉ hướng đi", "Được đặt trong khu đông dân cư, ở các đường giao nhau để chỉ dẫn hướng đi đến một địa danh lân cận tiếp theo và khoảng cách (km) đến nơi đó. Nếu biển này đặt trên đường cao tốc thì phía bên trái biển có thêm hình vẽ đường cao tốc.", "i415.png", directionSignId);
			trafficSignRepo.save(i415);
			TrafficSign i416 = new TrafficSign("I.416", "Lối đi đường vòng tránh", "Đặt trước các đường giao nhau, để chỉ dẫn lối đi đường tránh, đường vòng trong trường hợp đường chính bị tắc, hoặc đường chính cấm một số loại xe đi qua.", "i416.png", directionSignId);
			trafficSignRepo.save(i416);
			TrafficSign i418 = new TrafficSign("I.418", "Lối đi ở những vị trí cấm rẽ", "Để chỉ lối đi ở các nơi đường giao nhau bị cấm rẽ. Biển được đặt ở nơi đường giao nhau trước đường cấm rẽ.", "i418.png", directionSignId);
			trafficSignRepo.save(i418);
			TrafficSign i419 = new TrafficSign("I.419", "Chỉ dẫn địa giới", "Để chỉ dẫn địa giới hành chính giữa các thành phố, tỉnh, huyện.", "i419.png", directionSignId);
			trafficSignRepo.save(i419);
			TrafficSign i423a = new TrafficSign("I.423a", "Đường người đi bộ sang ngang", "Để chỉ dẫn cho người đi bộ và người lái xe biết nơi dành cho người đi bộ sang ngang. Biển này được sử dụng độc lập ở những vị trí sang ngang, đường không có tổ chức điều khiển giao thông hoặc có thể sử dụng phối hợp với vạch kẻ đường. Gặp biển này người lái xe phải điều khiển xe chạy chậm, chú ý quan sát, ưu tiên cho người đi bộ sang ngang.", "i423a.png", directionSignId);
			trafficSignRepo.save(i423a);
			TrafficSign i437 = new TrafficSign("I.437", "Đường cao tốc", "Để chỉ dẫn bắt đầu đường cao tốc.", "i437.png", directionSignId);
			trafficSignRepo.save(i437);
			TrafficSign i438 = new TrafficSign("I.438", "Hết đường cao tốc", "Để chỉ hết đường cao tốc.", "i438.png", directionSignId);
			trafficSignRepo.save(i438);
			TrafficSign i443 = new TrafficSign("I.443", "Xe kéo moóc", "Để báo hiệu xe có kéo moóc hoặc xe kéo xe, biển này được đặt trên nóc xe kéo.", "i443.png", directionSignId);
			trafficSignRepo.save(i443);
			TrafficSign i446 = new TrafficSign("I.446", "Nơi đỗ xe dành cho người tàn tật", "Để báo hiệu nơi đỗ xe dành cho người tàn tật.", "i446.png", directionSignId);
			trafficSignRepo.save(i446);
			TrafficSign i447a = new TrafficSign("I.447a", "Biển báo cầu vượt liên thông", "Biển đặt tại vị trí trước khi vào cầu vượt có tổ chức giao thông liên thông giữa các tuyến. Tuỳ theo nút giao mà bố trí biển số 447a, 447b, 445c, 447d cho phù hợp.", "i447a.png", directionSignId);
			trafficSignRepo.save(i447a);
			
			// Biển báo phụ
			TrafficSign s501 = new TrafficSign("S.501", "Phạm vi tác dụng của biển", "Để thông báo chiều dài đoạn đường nguy hiểm hoặc cấm hoặc hạn chế bên dưới một số biển báo nguy hiểm, biển báo cấm hoặc hạn chế, chẳng hạn như: Nhiều chỗ ngoặt nguy hiểm liên tiếp; Dốc xuống nguy hiểm...", "s501.png", subSignId);
			trafficSignRepo.save(s501);
			TrafficSign s502 = new TrafficSign("S.502", "Khoảng cách đến đối tượng báo hiệu", "Bên dưới các loại biển báo nguy hiểm, biển báo cấm, biển hiệu lệnh và chỉ dẫn, thông báo khoảng cách thực tế từ vị trí đặt biển đến đối tượng báo hiệu ở phía trước.", "s502.png", subSignId);
			trafficSignRepo.save(s502);
			TrafficSign s503a = new TrafficSign("S.503a", "Hướng tác dụng của biển", "Hướng tác dụng của biển.", "s503a.png", subSignId);
			trafficSignRepo.save(s503a);
			TrafficSign s504 = new TrafficSign("S.504", "Làn đường", "Biển số 504 được đặt bên trên làn đường và dưới các biển báo cấm và biển hiệu lệnh hay bên dưới đèn tín hiệu để chỉ làn đường chịu hiệu lực của biển báo hay đèn tín hiệu.", "s504.png", subSignId);
			trafficSignRepo.save(s504);
			TrafficSign s505a = new TrafficSign("S.505a", "Loại xe", "Được đặt bên dưới các biển báo cấm và biển hiệu lệnh hay biển chỉ dẫn để chỉ loại xe chịu hiệu lực của biển báo cấm, biển hiệu lệnh hay biển chỉ dẫn. Tùy theo loại xe chịu hiệu lực mà bố trí hình vẽ cho phù hợp.", "s505a.png", subSignId);
			trafficSignRepo.save(s505a);
			TrafficSign s506a = new TrafficSign("S.506a", "Hướng đường ưu tiên", "Được đặt bên dưới biển chỉ dẫn số 401 trên đường ưu tiên để chỉ dẫn cho người lái xe trên đường này biết hướng đường ưu tiên ở ngã tư.", "s506a.png", subSignId);
			trafficSignRepo.save(s506a);
			
			
			// Vạch kẻ đường
			TrafficSign v1_1 = new TrafficSign("V.1_1", "Vạch số 1.1", "Vạch liền nét màu trắng, rộng 10cm, Phân chia 2 dòng phương tiện giao thông đi ngược chiều nhau, xe không được đè lên vạch.", "v1_1.png", roadMarkingId);
			trafficSignRepo.save(v1_1);
			TrafficSign v1_2 = new TrafficSign("V.1_2", "Vạch số 1.2", "Vạch liền nét màu trắng, rộng 20cm, Xác định mép phần xe cơ giới với phần xe thô sơ, người  đi bộ hoặc lề đường trên các trục đường, xe chạy được phép đè lên vạch khi cần thiết.", "v1_2.png", roadMarkingId);
			trafficSignRepo.save(v1_2);
			TrafficSign v1_3 = new TrafficSign("V.1_3", "Vạch số 1.3", "Hai vạch liên tục màu trắng (vạch kép) có chiều rộng bằng nhau và bằng 10cm cách nhau là 10cm tính từ 2 mép vạch kề nhau, phân  chia  2  dòng  phương  tiện  giao  thông  đi  ngược chiều nhau trên những đường có từ 4 làn xe trở lên, xe không được đè lên vạch.", "v1_3.png", roadMarkingId);
			trafficSignRepo.save(v1_3);
			TrafficSign v1_4 = new TrafficSign("V.1_4", "Vạch số 1.4", "Vạch liên tục màu vàng có chiều rộng 10cm, xác định nơi cấm dừng và cấm đỗ xe, áp dụng độc lập hoặc có thể kết hợp với biển báo cấm số 130 \"Cấm dừng xe và đỗ xe\" và kẻ ở mép đường hay ở trên hàng vỉa nơi có vỉa hè.", "v1_4.png", roadMarkingId);
			trafficSignRepo.save(v1_4);
			TrafficSign v1_5 = new TrafficSign("V.1_5", "Vạch số 1.5", "Vạch đứt quãng màu trắng, rộng 10cm, phân chia 2 dòng phương tiện giao thông đi ngược chiều nhau trên những đường có 2 hoặc 3 làn xe chạy hoặc xác định danh giới làn xe khi có từ 2 làn xe trở lên chạy theo một chiều.", "v1_5.png", roadMarkingId);
			trafficSignRepo.save(v1_5);
			
		}
	}
	private void LoadQuestionSet() {
		if(questionSetRepo.count() == 0) {
			for(int c = 1; c <= 8; c++) {
				QuestionSet set = new QuestionSet("Đề " + c, false, 25, 0,0);
				questionSetRepo.save(set);
			}
		}
		
	}
	private void LoadQuestionType() {
		if(questionTypeRepo.count() == 0) {
			QuestionType conceptsAndRules = new QuestionType("KN", "Khái niệm và quy tắc giao thông", "Khái niệm và quy tắc giao thông");
			questionTypeRepo.save(conceptsAndRules);
			QuestionType cultureAndEthics = new QuestionType("VH", "Văn hóa và đạo đức", "Văn hóa và đạo đức");
			questionTypeRepo.save(cultureAndEthics);
			QuestionType drivingTechnique = new QuestionType("KT", "Kỹ thuật lái xe", "Kỹ thuật lái xe");
			questionTypeRepo.save(drivingTechnique);
			QuestionType roadSigns = new QuestionType("BB", "Biển báo đường bộ", "Biển báo đường bộ");
			questionTypeRepo.save(roadSigns);
			QuestionType pictures = new QuestionType("SH", "Sa hình", "Sa hình");
			questionTypeRepo.save(pictures);
		}
	}
	private void LoadQuestion() {
		License a1 = licenseRepo.findByName("A1");
		QuestionType kn = questionTypeRepo.findByCode("KN");
		QuestionType vh = questionTypeRepo.findByCode("VH");
		QuestionType kt = questionTypeRepo.findByCode("KT");
		QuestionType bb = questionTypeRepo.findByCode("BB");
		QuestionType sh = questionTypeRepo.findByCode("SH");
		QuestionSet set1 = questionSetRepo.findByName("Đề 1");
		QuestionSet set2 = questionSetRepo.findByName("Đề 2");
		QuestionSet set3 = questionSetRepo.findByName("Đề 3");
		QuestionSet set4 = questionSetRepo.findByName("Đề 4");
		QuestionSet set5 = questionSetRepo.findByName("Đề 5");
		QuestionSet set6 = questionSetRepo.findByName("Đề 6");
		QuestionSet set7 = questionSetRepo.findByName("Đề 7");
		QuestionSet set8 = questionSetRepo.findByName("Đề 8");
		if(questionRepo.count() == 0) {
            Question questionA1_1 = new Question(
					1, "Phần của đường bộ được sử dụng cho các phương tiện giao thông  qua lại là gì?",
					true, "", a1.getId(), set1.getId(), kn.getId());
			questionRepo.save(questionA1_1);

			Question questionA1_9 = new Question(
					2, "\"Phương tiện tham gia giao thông đường bộ\" gồm những loại nào?",
					true, "", a1.getId(), set1.getId(), kn.getId());
			questionRepo.save(questionA1_9);

			Question questionA1_17 = new Question(
					3, "Sử dụng rượu, bia khi lái xe, nếu bị phát hiện thì bị xử lý như thế nào?",
					true, "", a1.getId(), set1.getId(), kn.getId());
			questionRepo.save(questionA1_17);

			Question questionA1_25 = new Question(
					4, "Bạn đang lái xe phía trước có một xe cứu thương đang phát tín hiệu ưu tiên bạn có được phép vượt hay không?",
					true, "", a1.getId(), set1.getId(), kn.getId());
			questionRepo.save(questionA1_25);

			Question questionA1_33 = new Question(
					5, "Hành vi sử dụng xe mô tô để kéo, đẩy xe mô tô khác bị hết xăng đến trạm mua xăng có được phép hay không?",
					true, "", a1.getId(), set1.getId(), kn.getId());
			questionRepo.save(questionA1_33);
			
			Question questionA1_41 = new Question(
					6, "Biển báo hiệu hình tròn có nền xanh lam có hình vẽ màu trắng là loại biển gì dưới đây?",
					true, "a1_41.jpg", a1.getId(), set1.getId(), kn.getId());
			questionRepo.save(questionA1_41);
			
			Question questionA1_49 = new Question(
					7, "Bạn đang lái xe trong khu vực đô thị từ 22 giờ đến 5 giờ sáng hôm sau và cần vượt một xe khác, bạn cần báo hiệu như thế nào để đảm bảo an toàn giao thông?",
					true, "", a1.getId(), set1.getId(), kn.getId());
			questionRepo.save(questionA1_49);
			
			Question questionA1_57 = new Question(
					8, "Người điều khiển phương tiện tham gia giao thông trong hầm đường bộ" 
						+ " ngoài việc phải tuân thủ các quy tắc giao thông còn phải thực hiện những quy định nào dưới đây?",
					true, "", a1.getId(), set1.getId(), kn.getId());
			questionRepo.save(questionA1_57);
			
			Question questionA1_65 = new Question(
					9, "Trên đoạn đường hai chiều không có giải phân cách giữa, người lái xe không được vượt xe khác trong các trường hợp nào dưới đây?",
					true, "", a1.getId(), set1.getId(), kn.getId());
			questionRepo.save(questionA1_65);
			
			Question questionA1_73 = new Question(
					10, "Khi điều khiển xe chạy với tốc độ dưới 60 km/h, để đảm bảo khoảng cách an toàn giữa hai xe, người lái xe phải điều khiển xe như thế nào?",
					true, "", a1.getId(), set1.getId(), kn.getId());
			questionRepo.save(questionA1_73);
			
			Question questionA1_81 = new Question(
					11, "Để báo hiệu cho xe phía trước biết xe mô tô của bạn muốn vượt,"
						+ " bạn phải có tín hiệu như thế nào dưới đây?",
					true, "", a1.getId(), set1.getId(), kn.getId());
			questionRepo.save(questionA1_81);
			
			Question questionA1_89 = new Question(
					12, "Khi điều khiển xe mô tô tay ga xuống đường dốc dài, độ dốc cao, người lái xe cần thực hiện các thao tác nào dưới đây để đảm bảo an toàn?",
					true, "", a1.getId(), set1.getId(), kt.getId());
			questionRepo.save(questionA1_89);
			
			Question questionA1_97 = new Question(
					13, "Tay ga trên xe mô tô hai bánh có tác dụng gì trong các trường hợp dưới đây?",
					true, "", a1.getId(), set1.getId(), kt.getId());
			questionRepo.save(questionA1_97);
			
			Question questionA1_105 = new Question(
					14, "Biển nào cấm xe rẽ trái?",
					true, "a1_105.jpg", a1.getId(), set1.getId(), bb.getId());
			questionRepo.save(questionA1_105);
			
			Question questionA1_113 = new Question(
					15, "Biển nào dưới đây các phương tiện không được phép đi vào?",
					true, "a1_113.jpg", a1.getId(), set1.getId(), bb.getId());
			questionRepo.save(questionA1_113);
			
			Question questionA1_121 = new Question(
					16, "Biển nào xe mô tô hai bánh không được đi vào?",
					true, "a1_121.jpg", a1.getId(), set1.getId(), bb.getId());
			questionRepo.save(questionA1_121);
			
			Question questionA1_129 = new Question(
					17, "Biển nào báo hiệu nguy hiểm giao nhau với đường sắt?",
					true, "a1_129.jpg", a1.getId(), set1.getId(), bb.getId());
			questionRepo.save(questionA1_129);
			
            Question questionA1_137 = new Question(
					18, "Biển nào báo hiệu \"Đường giao nhau\" của các tuyến đường cùng cấp?",
					true, "a1_137.jpg", a1.getId(), set1.getId(), bb.getId());
			questionRepo.save(questionA1_137);	
			
			Question questionA1_145 = new Question(
					19, "Biển nào chỉ dẫn nơi bắt đầu đoạn đường dành cho người đi bộ?",
					true, "a1_145.jpg", a1.getId(), set1.getId(), bb.getId());
			questionRepo.save(questionA1_145);
			
			Question questionA1_153 = new Question(
					20, "Biển nào dưới đây báo hiệu hết cấm vượt?",
					true, "a1_153.jpg", a1.getId(), set1.getId(), bb.getId());
			questionRepo.save(questionA1_153);
			
			Question questionA1_161 = new Question(
					21, "Vạch kẻ đường nào dưới đây là vạch phân chia hai chiều xe chạy (vạch tim đường), xe không được lấn làn, không được đè lên vạch?",
					true, "a1_161.jpg", a1.getId(), set1.getId(), bb.getId());
			questionRepo.save(questionA1_161);

			Question questionA1_169 = new Question(
					22, "Xe nào được quyền đi trước trong trường hợp này?",
					true, "a1_169.jpg", a1.getId(), set1.getId(), sh.getId());
			questionRepo.save(questionA1_169);
            
            Question questionA1_177 = new Question(
					23, "Xe tải kéo mô tô ba bánh như hình này có đúng quy tắc giao thông không?",
					true, "a1_177.jpg", a1.getId(), set1.getId(), sh.getId());
			questionRepo.save(questionA1_177);
			
			Question questionA1_185 = new Question(
					24, "Theo hướng mũi tên, những hướng nào xe mô tô được phép đi?",
					true, "a1_185.jpg", a1.getId(), set1.getId(), sh.getId());
			questionRepo.save(questionA1_185);

			Question questionA1_193 = new Question(
					25, "Các xe đi theo hướng mũi tên, xe nào vi phạm quy tắc giao thông?",
					true, "a1_193.jpg", a1.getId(), set1.getId(), sh.getId());
			questionRepo.save(questionA1_193);

            
            Question questionA1_2 = new Question(
					1, "\"Làn đường\" là gì?",
					true, "", a1.getId(), set2.getId(), kn.getId());
			questionRepo.save(questionA1_2);

			Question questionA1_10 = new Question(
					2, "\"Người tham gia giao thông đường bộ\" gồm những đối tượng nào?",
					true, "", a1.getId(), set2.getId(), kn.getId());
			questionRepo.save(questionA1_10);

			Question questionA1_18 = new Question(
					3, "Theo Luật phòng chống tác hại của rượu, bia, đối tượng nào dưới đây bị cấm sử dụng rượu bia khi tham gia giao thông?",
					true, "", a1.getId(), set2.getId(), kn.getId());
			questionRepo.save(questionA1_18);
			
			Question questionA1_26 = new Question(
					4, "Người điều khiển xe mô tô hai bánh, ba bánh, xe gắn máy" + 
						" có được phép sử dụng xe để kéo hoặc đẩy các phương tiện khác khi tham gia giao thông không?",
					true, "", a1.getId(), set2.getId(), kn.getId());
			questionRepo.save(questionA1_26);
			
			Question questionA1_36 = new Question(
					5, "Người đủ 16 tuổi được điều khiển các loại xe nào dưới đây?",
					true, "", a1.getId(), set2.getId(), kn.getId());
			questionRepo.save(questionA1_36);
			
			Question questionA1_42 = new Question(
					6, "Biển báo hiệu hình chữ nhật hoặc hình vuông hoặc hình mũi tên nền xanh lam là loại biển gì dưới đây?",
					true, "a1_42.jpg", a1.getId(), set2.getId(), kn.getId());
			questionRepo.save(questionA1_42);
			
			Question questionA1_50 = new Question(
					7, "Khi điều khiển xe chạy trên đường biết có xe sau xin vượt nếu đủ điều kiện an toàn người lái xe phải làm gì?",
					true, "", a1.getId(), set2.getId(), kn.getId());
			questionRepo.save(questionA1_50);
			
			Question questionA1_58 = new Question(
					8, "Người ngồi trên xe mô tô 2 bánh, xe gắn máy phải đội mũ bảo hiểm có cài quai đúng quy cách khi nào?",
					true, "", a1.getId(), set2.getId(), kn.getId());
			questionRepo.save(questionA1_58);
			
			Question questionA1_66 = new Question(
					9, "Người lái xe mô tô xử lý như thế nào khi cho xe mô tô phía sau vượt?",
					true, "", a1.getId(), set2.getId(), kn.getId());
			questionRepo.save(questionA1_66);
			
			Question questionA1_74 = new Question(
					10, "Người lái xe phải giảm tốc độ thấp hơn tốc độ tối đa cho phép đến mức cần thiết,"
							+ " chú ý quan sát và chuẩn bị sẵn sàng những tình huống có thế xảy ra"
							+ " để phòng ngừa tai nạn trong các trường hợp nào dưới đây?",
					true, "", a1.getId(), set2.getId(), kn.getId());
			questionRepo.save(questionA1_74);
			
			Question questionA1_82 = new Question(
					11, "Người điều khiển xe mô tô phải giảm tốc độ và hết sức thận trọng"
						+ " khi qua những đoạn đường nào dưới đây?",
					true, "", a1.getId(), set2.getId(), kn.getId());
			questionRepo.save(questionA1_82);
			
			Question questionA1_90 = new Question(
					12, "Khi quay đầu xe, người lái xe cần phải quan sát và thực hiện các thao tác nào để đảm bảo an toàn giao thông?",
					true, "", a1.getId(), set2.getId(), kt.getId());
			questionRepo.save(questionA1_90);
			
			Question questionA1_98 = new Question(
					13, "Gương chiếu hậu của xe mô tô hai bánh, có tác dụng gì trong các trường hợp dưới đây?",
					true, "", a1.getId(), set2.getId(), kt.getId());
			questionRepo.save(questionA1_98);
			
			Question questionA1_106 = new Question(
					14, "Khi gặp biển nào xe được rẽ trái?",
					true, "a1_106.jpg", a1.getId(), set2.getId(), bb.getId());
			questionRepo.save(questionA1_106);
			
			Question questionA1_114 = new Question(
					15, "Khi gặp biển nào xe ưu tiên theo luật định vẫn phải dừng lại?",
					true, "a1_114.jpg", a1.getId(), set2.getId(), bb.getId());
			questionRepo.save(questionA1_114);
			
			Question questionA1_122 = new Question(
					16, "Biển báo nào báo hiệu bắt đầu đoạn đường vào phạm vi khu dân cư, "
							+ " các phương tiện tham gia giao thông phải tuân theo các quy định đi đường"
							+ " được áp dụng ở khu đông dân cư?",
					true, "a1_122.jpg", a1.getId(), set2.getId(), bb.getId());
			questionRepo.save(questionA1_122);
			
			Question questionA1_130 = new Question(
					17, "Biển nào báo hiệu đường sắt giao nhau với đường bộ không có rào chắn?",
					true, "a1_130.jpg", a1.getId(), set2.getId(), bb.getId());
			questionRepo.save(questionA1_130);
			
			Question questionA1_138 = new Question(
					18, "Biển nào báo hiệu \"Đường đôi\"?",
					true, "a1_138.jpg", a1.getId(), set2.getId(), bb.getId());
			questionRepo.save(questionA1_138);
			
			Question questionA1_146 = new Question(
					19, "Biển báo này có ý nghĩa gì?",
					true, "a1_146.jpg", a1.getId(), set2.getId(), bb.getId());
			questionRepo.save(questionA1_146);
			
			Question questionA1_154 = new Question(
					20, "Trong các biển dưới đây biển nào là biển \"Hết mọi lệnh cấm\"?",
					true, "a1_154.jpg", a1.getId(), set2.getId(), bb.getId());
			questionRepo.save(questionA1_154);
			
			Question questionA1_162 = new Question(
					21, "Vạch kẻ đường nào dưới đây là vạch phân chia hai chiều xe chạy (vạch tim đường)?",
					true, "a1_162.jpg", a1.getId(), set2.getId(), bb.getId());
			questionRepo.save(questionA1_162);
			
			Question questionA1_170 = new Question(
					22, "Theo tín hiệu đèn, xe nào được phép đi?",
					true, "a1_170.jpg", a1.getId(), set2.getId(), sh.getId());
			questionRepo.save(questionA1_170);
			
			Question questionA1_178 = new Question(
					23, "Xe nào được quyền đi trước trong trường hợp này?",
					true, "a1_178.jpg", a1.getId(), set2.getId(), sh.getId());
			questionRepo.save(questionA1_178);
			
			Question questionA1_186 = new Question(
					24, "Trong trường hợp này, thứ tự xe đi như thế nào là đúng quy tắc giao thông?",
					true, "a1_186.jpg", a1.getId(), set2.getId(), sh.getId());
			questionRepo.save(questionA1_186);
			
			Question questionA1_194 = new Question(
					25, "Các xe đi theo hướng mũi tên, xe nào chấp hành đúng quy tắc giao thông?",
					true, "a1_194.jpg", a1.getId(), set2.getId(), sh.getId());
			questionRepo.save(questionA1_194);
            
            Question questionA1_3 = new Question(
					1, "Trong các khái niệm dưới đây, \"dải phân cách\" được hiểu như thế nào là đúng?",
					true, "", a1.getId(), set3.getId(), kn.getId());
			questionRepo.save(questionA1_3);
			
			Question questionA1_11 = new Question(
					2, "\"Người điều khiển phương tiện tham gia giao thông đường bộ\"" 
						+ " gồm những đối tượng nào dưới đây?",
					true, "", a1.getId(), set3.getId(), kn.getId());
			questionRepo.save(questionA1_11);
			
			Question questionA1_19 = new Question(
					3, "Hành vi điều khiển xe cơ giới chạy quá tốc độ quy định, giành đường, vượt ẩu có bị nghiêm cấm hay không?",
					true, "", a1.getId(), set3.getId(), kn.getId());
			questionRepo.save(questionA1_19);
			
			Question questionA1_27 = new Question(
					4, "Khi điều khiển xe mô tô hai bánh, xe mô tô ba bánh, xe gắn máy," 
						+ " những hành vi buông cả hai tay; sử dụng xe để kéo, đẩy xe khác, vật khác;" 
						+ " sử dụng chân chống của xe quệt xuống đường khi xe đang chạy có được phép hay không?",
					true, "", a1.getId(), set3.getId(), kn.getId());
			questionRepo.save(questionA1_27);
			
			Question questionA1_35 = new Question(
					5, "Người đủ bao nhiêu tuổi trở lên thì được điều khiển xe mô tô hai bánh," 
						+ " xe mô tô ba bánh có dung tích xi lanh từ 50 cm3 trở lên và các loại xe có kết cấu tương tự;" 
						+ " xe ô tô tải, máy kéo có trọng tải dưới 3,5 tấn; xe ô tô chở người đến 9 chỗ ngồi?",
					true, "", a1.getId(), set3.getId(), kn.getId());
			questionRepo.save(questionA1_35);
			
			Question questionA1_43 = new Question(
					6, "Khi sử dụng giấy phép lái xe đã khai báo mất để điều khiển phương tiện cơ giới đường bộ," 
						+ " ngoài việc bị thu hồi giấy phép lái xe, chịu trách nhiệm trước pháp luật," 
						+ " người lái xe không được cấp giấy phép lái xe trong thời gian bao nhiêu năm?",
					true, "", a1.getId(), set3.getId(), kn.getId());
			questionRepo.save(questionA1_43);
			
			Question questionA1_51 = new Question(
					7, "Khi muốn chuyển hướng, người lái xe phải thực hiện như thế nào để đảm bảo an toàn giao thông?",
					true, "", a1.getId(), set3.getId(), kn.getId());
			questionRepo.save(questionA1_51);
			
			Question questionA1_59 = new Question(
					8, "Người điểu khiển xe mô tô hai bánh, xe gắn máy được phép chở tối đa 2 người trong những trường hợp nào?",
					true, "", a1.getId(), set3.getId(), kn.getId());
			questionRepo.save(questionA1_59);
			
			Question questionA1_67 = new Question(
					9, "Trong các trường hợp dưới đây, để đảm bảo an toàn khi tham gia giao thông," 
						+ " người lái xe mô tô cần thực hiện như thế nào?",
					true, "", a1.getId(), set3.getId(), kn.getId());
			questionRepo.save(questionA1_67);
			
			Question questionA1_75 = new Question(
					10, "Các phương tiện tham gia giao thông đường bộ (kể cả những xe có quyền ưu tiên)"
						+ " đều phải dừng lại bên phải đường của mình và trước vạch \"dừng xe\""
						+ " tại các điểm giao cắt giữa đường bộ và đường sắt khi có báo hiệu dừng nào dưới đây?",
					true, "", a1.getId(), set3.getId(), kn.getId());
			questionRepo.save(questionA1_75);
			
			Question questionA1_83 = new Question(
					11, "Khi gặp xe buýt đang đang dừng đón, trả khách," 
						+ " người điều khiển xe mô tô phải xử lý như thế nào dưới đây"
						+ " để đảm bảo an toàn giao thông?",
					true, "", a1.getId(), set3.getId(), kn.getId());
			questionRepo.save(questionA1_83);
			
			Question questionA1_91 = new Question(
					12, "Khi tránh nhau trên đường hẹp, người lái xe cần phải chú ý những điểm nào" 
						+ " để đảm bảo an toàn giao thông?",
					true, "", a1.getId(), set3.getId(), kt.getId());
			questionRepo.save(questionA1_91);
			
			Question questionA1_99 = new Question(
					13, "Để đảm bảo an toàn khi tham gia giao thông, người lái xe lái xe mô tô hai bánh" 
						+ " cần điều khiển tay ga như thế nào trong các trường hợp dưới đây?",
					true, "", a1.getId(), set3.getId(), kt.getId());
			questionRepo.save(questionA1_99);
			
			Question questionA1_107 = new Question(
					14, "Biển nào cấm các phương tiện giao thông đường bộ rẽ phải?",
					true, "a1_107.jpg", a1.getId(), set3.getId(), bb.getId());
			questionRepo.save(questionA1_107);
			
			Question questionA1_115 = new Question(
					15, "Biển nào cấm tất cả các loại xe cơ giới và thô sơ đi lại trên đường,"
							+ " trừ xe ưu tiên theo luật định (nếu đường vẫn cho xe chạy được)?",
					true, "a1_115.jpg", a1.getId(), set3.getId(), bb.getId());
			questionRepo.save(questionA1_115);
			
			Question questionA1_123 = new Question(
					16, "Gặp biển nào người lái xe phải nhường đường cho người đi bộ ?",
					true, "a1_123.jpg", a1.getId(), set3.getId(), bb.getId());
			questionRepo.save(questionA1_123);
			
			Question questionA1_131 = new Question(
					17, "Biển nào báo hiệu sắp đến chỗ giao nhau giữa đường bộ và đường sắt?",
					true, "a1_131.jpg", a1.getId(), set3.getId(), bb.getId());
			questionRepo.save(questionA1_131);
			
			Question questionA1_139 = new Question(
					18, "Biển nào báo hiệu \"Đường đôi\"?",
					true, "a1_139.jpg", a1.getId(), set3.getId(), bb.getId());
			questionRepo.save(questionA1_139);
            
            Question questionA1_147 = new Question(
					19, "Biển nào (đặt trước ngã ba, ngã tư) cho phép xe được rẽ sang hướng khác?",
					true, "a1_147.jpg", a1.getId(), set3.getId(), bb.getId());
			questionRepo.save(questionA1_147);
			
			Question questionA1_155 = new Question(
					20, "Biển nào chỉ dẫn cho người đi bộ sử dụng cầu vượt qua đường?",
					true, "a1_155.jpg", a1.getId(), set3.getId(), bb.getId());
			questionRepo.save(questionA1_155);
			
			Question questionA1_163 = new Question(
					21, "Các vạch dưới đây có tác dụng gì?",
					true, "a1_163.jpg", a1.getId(), set3.getId(), bb.getId());
			questionRepo.save(questionA1_163);
			
			Question questionA1_171 = new Question(
					22, "Các xe đi theo hướng mũi tên, xe nào vi phạm quy tắc giao thông?",
					true, "a1_171.jpg", a1.getId(), set3.getId(), sh.getId());
			questionRepo.save(questionA1_171);

			Question questionA1_179 = new Question(
					23, "Thứ tự các xe đi như thế nào là đúng quy tắc giao thông?",
					true, "a1_179.jpg", a1.getId(), set3.getId(), sh.getId());
			questionRepo.save(questionA1_179);
            
            Question questionA1_187 = new Question(
					24, "Trong hình dưới, những xe nào vi phạm quy tắc giao thông?",
					true, "a1_187.jpg", a1.getId(), set3.getId(), sh.getId());
			questionRepo.save(questionA1_187);
			
			Question questionA1_195 = new Question(
					25, "Các xe đi theo thứ tự nào là đúng quy tắc giao thông đường bộ?",
					true, "a1_195.jpg", a1.getId(), set3.getId(), sh.getId());
			questionRepo.save(questionA1_195);

            Question questionA1_4 = new Question(
					1, "\"Dải phân cách\" trên đường bộ gồm những loại nào?",
					true, "", a1.getId(), set4.getId(), kn.getId());
			questionRepo.save(questionA1_4);
			
			Question questionA1_12 = new Question(
					2, "Khái niệm \"người điều khiển giao thông\" được hiểu như thế nào là đúng?",
					true, "", a1.getId(), set4.getId(), kn.getId());
			questionRepo.save(questionA1_12);
			
			Question questionA1_20 = new Question(
					3, "Khi lái xe trong khu đô thị và đông dân cư trừ các khu vực có biển cấm sử dụng còi," 
						+ " người lái xe được sử dụng còi như thế nào trong các trường hợp dưới đây?",
					true, "", a1.getId(), set4.getId(), kn.getId());
			questionRepo.save(questionA1_20);			
			
			Question questionA1_28 = new Question(
					4, "Khi điều khiển xe mô tô hai bánh, xe mô tô ba bánh, xe gắn máy," 
						+ " những hành vi nào không được phép?",
					true, "", a1.getId(), set4.getId(), kn.getId());
			questionRepo.save(questionA1_28);
			
			Question questionA1_34 = new Question(
					5, "Hành vi vận chuyển đồ vật cồng kềnh bằng xe mô tô, xe gắn máy khi tham gia giao thông có được phép hay không?",
					true, "", a1.getId(), set4.getId(), kn.getId());
			questionRepo.save(questionA1_34);
			
			Question questionA1_44 = new Question(
					6, "Khi gặp hiệu lệnh như dưới đây của cảnh sát giao thông thì người tham gia giao thông phải đi như thế nào là đúng quy tắc giao thông?",
					true, "a1_44.jpg", a1.getId(), set4.getId(), kn.getId());
			questionRepo.save(questionA1_44);
			
			Question questionA1_52 = new Question(
					7, "Khi tránh xe đi ngược chiều, các xe phải nhường đường như thế nào là đúng quy tắc giao thông?",
					true, "", a1.getId(), set4.getId(), kn.getId());
			questionRepo.save(questionA1_52);
			
			Question questionA1_60 = new Question(
					8, "Người điều khiển xe mô tô hai bánh, xe gắn máy không được thực hiện những hành vi nào dưới đây?",
					true, "", a1.getId(), set4.getId(), kn.getId());
			questionRepo.save(questionA1_60);
			
			Question questionA1_68 = new Question(
					9, "Đường bộ trong khu vực đông dân cư gồm những đoạn đường nào dưới đây?",
					true, "", a1.getId(), set4.getId(), kn.getId());
			questionRepo.save(questionA1_68);
			
			Question questionA1_76 = new Question(
					10, "Tác dụng của mũ bảo hiểm đối với người ngồi trên xe mô tô hai bánh trong trường hợp xảy ra tai nạn giao thông là gì?",
					true, "", a1.getId(), set4.getId(), kn.getId());
			questionRepo.save(questionA1_76);
			
			Question questionA1_84 = new Question(
					11, "Khái niệm về văn hóa giao thông được hiểu như thế nào là đúng?",
					true, "", a1.getId(), set4.getId(), vh.getId());
			questionRepo.save(questionA1_84);
			
			Question questionA1_92 = new Question(
					12, "Khi điều khiển xe trên đường vòng người lái xe cần phải làm gì để đảm bảo an toàn?",
					true, "", a1.getId(), set4.getId(), kt.getId());
			questionRepo.save(questionA1_92);
			
			Question questionA1_100 = new Question(
					13, "Kỹ thuật cơ bản để giữ thăng bằng khi điều khiển xe mô tô đi trên đường gồ ghề như thế nào trong các trường hợp dưới đây?",
					true, "", a1.getId(), set4.getId(), kt.getId());
			questionRepo.save(questionA1_100);
			
			Question questionA1_108 = new Question(
					14, "Biển nào cấm các phương tiện giao thông đường bộ rẽ trái?",
					true, "a1_108.jpg", a1.getId(), set4.getId(), bb.getId());
			questionRepo.save(questionA1_108);
			
			Question questionA1_116 = new Question(
					15, "Gặp biển nào xe xích lô được phép đi vào?",
					true, "a1_116.jpg", a1.getId(), set4.getId(), bb.getId());
			questionRepo.save(questionA1_116);
			
			Question questionA1_124 = new Question(
					16, "Biển nào chỉ đường dành cho người đi bộ, các loại xe không được đi vào khi gặp biển này?",
					true, "a1_124.jpg", a1.getId(), set4.getId(), bb.getId());
			questionRepo.save(questionA1_124);
			
			Question questionA1_132 = new Question(
					17, "Biển nào báo hiệu, chỉ dẫn xe đi trên đường này được quyền ưu tiên qua nơi giao nhau?",
					true, "a1_132.jpg", a1.getId(), set4.getId(), bb.getId());
			questionRepo.save(questionA1_132);
			
			Question questionA1_140 = new Question(
					18, "Biển nào báo hiệu \"Giao nhau với đường hai chiều\"?",
					true, "a1_140.jpg", a1.getId(), set4.getId(), bb.getId());
			questionRepo.save(questionA1_140);
			
			Question questionA1_148 = new Question(
					19, "Biển nào báo hiệu \"Hướng đi thẳng phải theo\"?",
					true, "a1_148.jpg", a1.getId(), set4.getId(), bb.getId());
			questionRepo.save(questionA1_148);
			
			Question questionA1_156 = new Question(
					20, "Biển nào chỉ dẫn cho người đi bộ sử dụng hầm chui qua đường?",
					true, "a1_156.jpg", a1.getId(), set4.getId(), bb.getId());
			questionRepo.save(questionA1_156);
			
			Question questionA1_164 = new Question(
					21, "Khi gặp vạch kẻ đường nào các xe được phép đè vạch?",
					true, "a1_164.jpg", a1.getId(), set4.getId(), bb.getId());
			questionRepo.save(questionA1_164);
			
			Question questionA1_172 = new Question(
					22, "Thứ tự các xe đi như thế nào là đúng quy tắc giao thông?",
					true, "a1_172.jpg", a1.getId(), set4.getId(), sh.getId());
			questionRepo.save(questionA1_172);
			
			Question questionA1_180 = new Question(
					23, "Xe nào được quyền đi trước trong trường hợp này?",
					true, "a1_180.jpg", a1.getId(), set4.getId(), sh.getId());
			questionRepo.save(questionA1_180);
			
			Question questionA1_188 = new Question(
					24, "Trong hình dưới, những xe nào vi phạm quy tắc giao thông?",
					true, "a1_188.jpg", a1.getId(), set4.getId(), sh.getId());
			questionRepo.save(questionA1_188);
			
			Question questionA1_196 = new Question(
					25, "Các xe đi theo thứ tự nào là đúng quy tắc giao thông đường bộ?",
					true, "a1_196.jpg", a1.getId(), set4.getId(), sh.getId());
			questionRepo.save(questionA1_196);

            Question questionA1_5 = new Question(
					1, "Người lái xe được hiểu như thế nào trong các khái niệm dưới đây?",
					true, "", a1.getId(), set5.getId(), kn.getId());
			questionRepo.save(questionA1_5);
			
			Question questionA1_13 = new Question(
					2, "Trong các khái niệm dưới đây khái niệm \"dừng xe\" được hiểu như thế nào là đúng?",
					true, "", a1.getId(), set5.getId(), kn.getId());
			questionRepo.save(questionA1_13);
			
			Question questionA1_21 = new Question(
					3, "Người lái xe sử dụng đèn như thế nào khi lái xe trong khu đô thị và đông dân cư vào ban đêm?",
					true, "", a1.getId(), set5.getId(), kn.getId());
			questionRepo.save(questionA1_21);
			
			Question questionA1_29 = new Question(
					4, "Người ngồi trên xe mô tô hai bánh, ba bánh, xe gắn máy khi tham gia giao thông có được mang, vác vật cồng kềnh hay không?",
					true, "", a1.getId(), set5.getId(), kn.getId());
			questionRepo.save(questionA1_29);
			
			Question questionA1_37 = new Question(
					5, "Người có giấy phép lái xe mô tô hạng A1 không được phép điều khiển loại xe nào dưới đây?",
					true, "", a1.getId(), set5.getId(), kn.getId());
			questionRepo.save(questionA1_37);
			
			Question questionA1_45 = new Question(
					6, "Khi gặp hiệu lệnh như dưới đây của cảnh sát giao thông thì người tham gia giao thông phải đi như thế nào là đúng quy tắc giao thông?",
					true, "a1_45.jpg", a1.getId(), set5.getId(), kn.getId());
			questionRepo.save(questionA1_45);
			
			Question questionA1_53 = new Question(
					7, "Bạn đang lái xe trên đường hẹp, xuống dốc và gặp một xe đang đi lên dốc, bạn cần làm gì?",
					true, "", a1.getId(), set5.getId(), kn.getId());
			questionRepo.save(questionA1_53);
			
			Question questionA1_61 = new Question(
					8, "Người điều khiển xe mô tô hai bánh, xe gắn máy có được đi xe dàn hàng ngang;" 
						+ " đi xe vào phần đường dành cho người đi bộ và phương tiện khác;" 
						+ " sử dụng ô, điện thoại di động, thiết bị âm thanh (trừ thiết bị trợ thính) hay không?",
					true, "", a1.getId(), set5.getId(), kn.getId());
			questionRepo.save(questionA1_61);
			
			Question questionA1_69 = new Question(
					9, "Tốc độ tối đa cho phép đối với xe máy chuyên dùng, xe gắn máy (kể cả xe máy điện)" 
						+ " và các loại xe tương tự trên đường bộ (trừ đường cao tốc) không được vượt quá bao nhiêu km/h?",
					true, "", a1.getId(), set5.getId(), kn.getId());
			questionRepo.save(questionA1_69);
			
			Question questionA1_77 = new Question(
					10, "Tại nơi đường giao nhau, người lái xe đang đi trên đường không ưu tiên phải xử lý như thế nào là đúng quy tắc giao thông?",
					true, "", a1.getId(), set5.getId(), kn.getId());
			questionRepo.save(questionA1_77);
			
			Question questionA1_85 = new Question(
					11, "Trong các hành vi dưới đây, người lái xe mô tô có văn hóa giao thông phải ứng xử như thế nào?",
					true, "", a1.getId(), set5.getId(), vh.getId());
			questionRepo.save(questionA1_85);
			
			Question questionA1_93 = new Question(
					12, "Để đạt được hiệu quả phanh cao nhất, người lái xe mô tô phải sử dụng các kỹ năng như thế nào dưới đây?",
					true, "", a1.getId(), set5.getId(), kt.getId());
			questionRepo.save(questionA1_93);
			
			Question questionA1_101 = new Question(
					13, "Biển nào dưới đây xe gắn máy được phép đi vào?",
					true, "a1_101.jpg", a1.getId(), set5.getId(), bb.getId());
			questionRepo.save(questionA1_101);
			
			Question questionA1_109 = new Question(
					14, "Biển nào cho phép xe rẽ trái?",
					true, "a1_109.jpg", a1.getId(), set5.getId(), bb.getId());
			questionRepo.save(questionA1_109);
			
			Question questionA1_117 = new Question(
					15, "Gặp biển nào xe lam, xe xích lô máy được phép đi vào?",
					true, "a1_117.jpg", a1.getId(), set5.getId(), bb.getId());
			questionRepo.save(questionA1_117);
			
			Question questionA1_125 = new Question(
					16, "Biển nào báo hiệu \"Đường dành cho xe thô sơ\"?",
					true, "a1_125.jpg", a1.getId(), set5.getId(), bb.getId());
			questionRepo.save(questionA1_125);
			
			Question questionA1_133 = new Question(
					17, "Biển nào báo hiệu \"Giao nhau với đường không ưu tiên\"?",
					true, "a1_133.jpg", a1.getId(), set5.getId(), bb.getId());
			questionRepo.save(questionA1_133);
			
			Question questionA1_141 = new Question(
					18, "Biển nào báo hiệu \"Đường hai chiều\"?",
					true, "a1_141.jpg", a1.getId(), set5.getId(), bb.getId());
			questionRepo.save(questionA1_141);
			
			Question questionA1_149 = new Question(
					19, "Biển nào báo hiệu \"Đường một chiều\"?",
					true, "a1_139.jpg", a1.getId(), set5.getId(), bb.getId());
			questionRepo.save(questionA1_149);
            
            Question questionA1_157 = new Question(
					20, "Biển nào báo hiệu \"Nơi đỗ xe dành cho người khuyết tật\"?",
					true, "a1_157.jpg", a1.getId(), set5.getId(), bb.getId());
			questionRepo.save(questionA1_157);
			
			Question questionA1_165 = new Question(
					21, "Vạch dưới đây có ý nghĩa gì?",
					true, "a1_165.jpg", a1.getId(), set5.getId(), bb.getId());
			questionRepo.save(questionA1_165);
			
			Question questionA1_173 = new Question(
					22, "Trong trường hợp này xe nào đỗ vi phạm quy tắc giao thông?",
					true, "a1_173.jpg", a1.getId(), set5.getId(), sh.getId());
			questionRepo.save(questionA1_173);
			
			Question questionA1_181 = new Question(
					23, "Xe nào vi phạm quy tắc giao thông?",
					true, "a1_181.jpg", a1.getId(), set5.getId(), sh.getId());
			questionRepo.save(questionA1_181);

			Question questionA1_189 = new Question(
					24, "Bạn có được phép vượt xe mô tô phía trước không?",
					true, "a1_189.jpg", a1.getId(), set5.getId(), sh.getId());
			questionRepo.save(questionA1_189);
            
            Question questionA1_197 = new Question(
					25, "Bạn xử lý như thế nào trong trường hợp này?",
					true, "a1_197.jpg", a1.getId(), set5.getId(), sh.getId());
			questionRepo.save(questionA1_197);

            Question questionA1_6 = new Question(
					1, "Đường mà trên đó phương tiện tham gia giao thông được các phương tiện giao thông"
						+ " đến từ hướng khác nhường đường khi qua nơi đường giao nhau," 
						+ " được cắm biển báo hiệu đường ưu tiên là loại đường gì?",
					true, "", a1.getId(), set6.getId(), kn.getId());
			questionRepo.save(questionA1_6);
			
			Question questionA1_14 = new Question(
					2, "Khái niệm \"đỗ xe\" được hiểu như thế nào là đúng?",
					true, "", a1.getId(), set6.getId(), kn.getId());
			questionRepo.save(questionA1_14);
			
			Question questionA1_22 = new Question(
					3, "Trong trường hợp đặc biệt, để được lắp đặt, sử dụng còi, đèn không đúng với thiết kế của nhà sản xuất đối với từng loại xe cơ giới bạn phải đảm bảo yêu cầu nào dưới đây?",
					true, "", a1.getId(), set6.getId(), kn.getId());
			questionRepo.save(questionA1_22);
			
			Question questionA1_30 = new Question(
					4, "Người ngồi trên xe mô tô hai bánh, xe mô tô ba bánh, xe gắn máy khi tham gia giao thông có được bám, kéo hoặc đẩy các phương tiện khác không?",
					true, "", a1.getId(), set6.getId(), kn.getId());
			questionRepo.save(questionA1_30);
			
			Question questionA1_38 = new Question(
					5, "Người có giấy phép lái xe mô tô hạng A1 được phép điều khiển loại xe nào dưới đây?",
					true, "", a1.getId(), set6.getId(), kn.getId());
			questionRepo.save(questionA1_38);
			
			Question questionA1_46 = new Question(
					6, "Tại nơi có biển báo hiệu cố định lại có báo hiệu tạm thời thì người tham gia giao thông phải chấp hành hiệu lệnh của báo hiệu nào?",
					true, "", a1.getId(), set6.getId(), kn.getId());
			questionRepo.save(questionA1_46);
			
			Question questionA1_54 = new Question(
					7, "Tại nơi đường giao nhau, người lái xe đang đi trên đường không ưu tiên phải nhường đường như thế nào là đúng quy tắc giao thông?",
					true, "", a1.getId(), set6.getId(), kn.getId());
			questionRepo.save(questionA1_54);
			
			Question questionA1_62 = new Question(
					8, "Người lái xe phải giảm tốc độ thấp hơn tốc độ tối đa cho phép (có thể dừng lại một cách an toàn) trong trường hợp nào dưới đây?",
					true, "", a1.getId(), set6.getId(), kn.getId());
			questionRepo.save(questionA1_62);
			
			Question questionA1_70 = new Question(
					9, "Trên đường bộ (trừ đường cao tốc) trong khu vực đông dân cư, đường đôi có dải phân cách giữa, xe mô tô hai bánh, ô tô chở người đến 30 chỗ tham gia giao thông với tốc độ tối đa cho phép là bao nhiêu?",
					true, "", a1.getId(), set6.getId(), kn.getId());
			questionRepo.save(questionA1_70);
			
			Question questionA1_78 = new Question(
					10, "Người lái xe phải xử lý như thế nào khi quan sát phía trước thấy người đi bộ đang sang đường tại nơi có vạch đường dành cho người đi bộ để đảm bảo an toàn?",
					true, "", a1.getId(), set6.getId(), kn.getId());
			questionRepo.save(questionA1_78);
			
			Question questionA1_86 = new Question(
					11, "Trong các hành vi dưới đây, người lái xe ô tô, mô tô có văn hóa giao thông phải ứng xử như thế nào?",
					true, "", a1.getId(), set6.getId(), vh.getId());
			questionRepo.save(questionA1_86);
			
			Question questionA1_94 = new Question(
					12, "Khi đang lái xe mô tô và ô tô, nếu có nhu cầu sử dụng điện thoại để nhắn tin hoặc gọi điện," 
						+ " người lái xe phải thực hiện như thế nào trong các tình huống nêu dưới đây?",
					true, "", a1.getId(), set6.getId(), kt.getId());
			questionRepo.save(questionA1_94);
			
			Question questionA1_102 = new Question(
					13, "Biển nào báo hiệu cấm xe mô tô hai bánh đi vào?",
					true, "a1_102.jpg", a1.getId(), set6.getId(), bb.getId());
			questionRepo.save(questionA1_102);
			
			Question questionA1_110 = new Question(
					14, "Biển nào xe được phép quay đầu nhưng không được rẽ trái?",
					true, "a1_110.jpg", a1.getId(), set6.getId(), bb.getId());
			questionRepo.save(questionA1_110);
			
			Question questionA1_118 = new Question(
					15, "Biển báo này có ý nghĩa như thế nào?",
					true, "a1_118.jpg", a1.getId(), set6.getId(), bb.getId());
			questionRepo.save(questionA1_118);
			
			Question questionA1_126 = new Question(
					16, "Biển nào báo hiệu sắp đến chỗ giao nhau nguy hiểm?",
					true, "a1_126.jpg", a1.getId(), set6.getId(), bb.getId());
			questionRepo.save(questionA1_126);
			
			Question questionA1_134 = new Question(
					17, "Biển nào báo hiệu \"Giao nhau với đường ưu tiên\"?",
					true, "a1_134.jpg", a1.getId(), set6.getId(), bb.getId());
			questionRepo.save(questionA1_134);
			
			Question questionA1_142 = new Question(
					18, "Biển nào báo hiệu \"Giao nhau với đường hai chiều\"?",
					true, "a1_142.jpg", a1.getId(), set6.getId(), bb.getId());
			questionRepo.save(questionA1_142);
			
			Question questionA1_152 = new Question(
					19, "Trong các biển dưới đây biển nào là biển \"Hết tốc độ tối thiểu\"?",
					true, "a1_152.jpg", a1.getId(), set6.getId(), bb.getId());
			questionRepo.save(questionA1_152);
			
			Question questionA1_158 = new Question(
					20, "Gặp biển báo này, người tham gia giao thông phải xử lý như thế nào?",
					true, "a1_158.jpg", a1.getId(), set6.getId(), bb.getId());
			questionRepo.save(questionA1_158);
			
			Question questionA1_166 = new Question(
					21, "Thứ tự các xe đi như thế nào là đúng quy tắc giao thông?",
					true, "a1_166.jpg", a1.getId(), set6.getId(), sh.getId());
			questionRepo.save(questionA1_166);
			
			Question questionA1_174 = new Question(
					22, "Theo hướng mũi tên, những hướng nào xe gắn máy đi được?",
					true, "a1_174.jpg", a1.getId(), set6.getId(), sh.getId());
			questionRepo.save(questionA1_174);
			
			Question questionA1_182 = new Question(
					23, "Các xe đi như thế nào là đúng quy tắc giao thông?",
					true, "a1_182.jpg", a1.getId(), set6.getId(), sh.getId());
			questionRepo.save(questionA1_182);
			
			Question questionA1_190 = new Question(
					24, "Theo tín hiệu đèn của xe cơ giới, xe nào vi phạm quy tắc giao thông?",
					true, "a1_190.jpg", a1.getId(), set6.getId(), sh.getId());
			questionRepo.save(questionA1_190);
			
			Question questionA1_198 = new Question(
					25, "Xe nào dừng đúng theo quy tắc giao thông?",
					true, "a1_198.jpg", a1.getId(), set6.getId(), sh.getId());
			questionRepo.save(questionA1_198);

            Question questionA1_7 = new Question(
					1, "Khái niệm \"phương tiện giao thông cơ giới đường bộ\" được hiểu thế nào là đúng?",
					true, "", a1.getId(), set7.getId(), kn.getId());
			questionRepo.save(questionA1_7);
			
			Question questionA1_15 = new Question(
					2, "Cuộc đua xe chỉ được thực hiện khi nào?",
					true, "", a1.getId(), set7.getId(), kn.getId());
			questionRepo.save(questionA1_15);
			
			Question questionA1_23 = new Question(
					3, "Ở phần đường dành cho người đi bộ qua đường, trên cầu, đầu cầu, đường cao tốc, đường hẹp, đường dốc, tại nơi đường bộ giao nhau cùng mức với đường sắt có được quay đầu xe hay không?",
					true, "", a1.getId(), set7.getId(), kn.getId());
			questionRepo.save(questionA1_23);
			
			Question questionA1_31 = new Question(
					4, "Người ngồi trên xe mô tô hai bánh, xe mô tô ba bánh, xe gắn máy khi tham gia giao thông có được sử dụng ô khi trời mưa hay không?",
					true, "", a1.getId(), set7.getId(), kn.getId());
			questionRepo.save(questionA1_31);
			
			Question questionA1_39 = new Question(
					5, "Biển báo hiệu có dạng hình tròn, viền đỏ, nền trắng, trên nền có hình vẽ hoặc chữ số, chữ viết màu đen là loại biển gì dưới đây?",
					true, "a1_39.jpg", a1.getId(), set7.getId(), kn.getId());
			questionRepo.save(questionA1_39);
			
			Question questionA1_47 = new Question(
					6, "Trên đường có nhiều làn đường cho xe đi cùng chiều được phân biệt bằng vạch kẻ phân làn đường, người điều khiển phương tiện phải cho xe đi như thế nào?",
					true, "", a1.getId(), set7.getId(), kn.getId());
			questionRepo.save(questionA1_47);
			
			Question questionA1_55 = new Question(
					7, "Tại nơi đường giao nhau không có báo hiệu đi theo vòng xuyến, người điều khiển phương tiện phải nhường đường như thế nào là đúng quy tắc giao thông?",
					true, "", a1.getId(), set7.getId(), kn.getId());
			questionRepo.save(questionA1_55);
			
			Question questionA1_63 = new Question(
					8, "Tại ngã ba hoặc ngã tư không có đảo an toàn, người lái xe phải nhường đường như thế nào là đúng trong các trường hợp dưới đây?",
					true, "", a1.getId(), set7.getId(), kn.getId());
			questionRepo.save(questionA1_63);

			Question questionA1_71 = new Question(
					9, "Trên đường bộ (trừ đường cao tốc) trong khu vực đông dân cư, đường hai chiều không có dải phân cách giữa, xe mô tô hai bánh, ô tô chở người đến 30 chỗ tham gia giao thông với tốc độ tối đa cho phép là bao nhiêu?",
					true, "", a1.getId(), set7.getId(), kn.getId());
			questionRepo.save(questionA1_71);
			
			Question questionA1_79 = new Question(
					10, "Theo Luật Giao thông đường bộ, tín hiệu đèn giao thông gồm 3 màu nào dưới đây?",
					true, "", a1.getId(), set7.getId(), kn.getId());
			questionRepo.save(questionA1_79);
			
			Question questionA1_87 = new Question(
					11, "Khi xảy ra tai nạn giao thông, có người bị thương nghiêm trọng, người lái xe và người có mặt tại hiện trường vụ tai nạn phải thực hiện các công việc gì dưới đây?",
					true, "", a1.getId(), set7.getId(), vh.getId());
			questionRepo.save(questionA1_87);
			
			Question questionA1_95 = new Question(
					12, "Những thói quen nào dưới đây khi điều khiển xe mô tô tay ga tham gia giao thông dễ gây tai nạn nguy hiểm?",
					true, "", a1.getId(), set7.getId(), kt.getId());
			questionRepo.save(questionA1_95);
			
			Question questionA1_103 = new Question(
					13, "Khi gặp biển nào thì xe mô tô hai bánh được đi vào?",
					true, "a1_103.jpg", a1.getId(), set7.getId(), bb.getId());
			questionRepo.save(questionA1_103);
			
			Question questionA1_111 = new Question(
					14, "Biển nào xe được phép quay đầu nhưng không được rẽ trái?",
					true, "a1_111.jpg", a1.getId(), set7.getId(), bb.getId());
			questionRepo.save(questionA1_111);
			
			Question questionA1_119 = new Question(
					15, "Chiều dài đoạn đường 500m từ nơi đặt biển này, người lái xe có được phép bấm còi không?",
					true, "a1_119.jpg", a1.getId(), set7.getId(), bb.getId());
			questionRepo.save(questionA1_119);
			
			Question questionA1_127 = new Question(
					16, "Biển nào báo hiệu \"Giao nhau với đường sắt có rào chắn\"?",
					true, "a1_127.jpg", a1.getId(), set7.getId(), bb.getId());
			questionRepo.save(questionA1_127);
			
			Question questionA1_135 = new Question(
					17, "Biển nào báo hiệu \"Đường bị thu hẹp\"?",
					true, "a1_135.jpg", a1.getId(), set7.getId(), bb.getId());
			questionRepo.save(questionA1_135);
			
			Question questionA1_143 = new Question(
					18, "Biển nào báo hiệu \"Chú ý chướng ngại vật\"?",
					true, "a1_143.jpg", a1.getId(), set7.getId(), bb.getId());
			questionRepo.save(questionA1_143);
			
			Question questionA1_151 = new Question(
					19, "Hiệu lực của biển \"Tốc độ tối đa cho phép\" hết tác dụng khi gặp biển nào dưới đây?",
					true, "a1_151.jpg", a1.getId(), set7.getId(), bb.getId());
			questionRepo.save(questionA1_151);

			Question questionA1_159 = new Question(
					20, "Biển số 1 có ý nghĩa gì?",
					true, "a1_159.jpg", a1.getId(), set7.getId(), bb.getId());
			questionRepo.save(questionA1_159);
            
            Question questionA1_167 = new Question(
					21, "Thứ tự các xe đi như thế nào là đúng quy tắc giao thông?",
					true, "a1_167.jpg", a1.getId(), set7.getId(), sh.getId());
			questionRepo.save(questionA1_167);
			
			Question questionA1_175 = new Question(
					22, "Xe nào đỗ vi phạm quy tắc giao thông?",
					true, "a1_175.jpg", a1.getId(), set7.getId(), sh.getId());
			questionRepo.save(questionA1_175);
			
			Question questionA1_183 = new Question(
					23, "Theo hướng mũi tên, xe nào được phép đi?",
					true, "a1_183.jpg", a1.getId(), set7.getId(), sh.getId());
			questionRepo.save(questionA1_183);
			
			Question questionA1_191 = new Question(
					24, "Các xe đi theo hướng mũi tên, xe nào vi phạm quy tắc giao thông?",
					true, "a1_191.jpg", a1.getId(), set7.getId(), sh.getId());
			questionRepo.save(questionA1_191);

			Question questionA1_199 = new Question(
					25, "Xe của bạn đang di chuyển gần đến khu vực giao cắt với đường sắt,"
						+ " khi rào chắn đang dịch chuyển, bạn điều khiển xe như thế nào là đúng quy tắc giao thông?",
					true, "a1_199.jpg", a1.getId(), set7.getId(), sh.getId());
			questionRepo.save(questionA1_199);
			
			Question questionA1_8 = new Question(
					1, "Khái niệm \"phương tiện giao thông thô sơ đường bộ\" được hiểu thế nào là đúng?",
					true, "", a1.getId(), set8.getId(), kn.getId());
			questionRepo.save(questionA1_8);
			
			Question questionA1_16 = new Question(
					2, "Người điều khiển phương tiện giao thông đường bộ mà trong cơ thể có chất ma tuý có bị nghiêm cấm hay không?",
					true, "", a1.getId(), set8.getId(), kn.getId());
			questionRepo.save(questionA1_16);
			
			Question questionA1_24 = new Question(
					3, "Bạn đang lái xe phía trước có một xe cảnh sát giao thông không phát tín hiệu ưu tiên bạn có được phép vượt hay không?",
					true, "", a1.getId(), set8.getId(), kn.getId());
			questionRepo.save(questionA1_24);
			
			Question questionA1_32 = new Question(
					4, "Khi đang lên dốc người ngồi trên xe mô tô có được kéo theo người đang điều khiển xe đạp hay không?",
					true, "", a1.getId(), set8.getId(), kn.getId());
			questionRepo.save(questionA1_32);
			
			Question questionA1_40 = new Question(
					5, "Biển báo hiệu có dạng tam giác đều, viền đỏ, viền màu vàng, trên có hình vẽ màu đen là loại biển gì dưới đây?",
					true, "a1_40.jpg", a1.getId(), set8.getId(), kn.getId());
			questionRepo.save(questionA1_40);
			
			Question questionA1_48 = new Question(
					6, "Trên đường một chiều có vạch kẻ phân làn đường, xe thô sơ và xe cơ giới phải đi như thế nào là đúng quy tắc giao thông?",
					true, "", a1.getId(), set8.getId(), kn.getId());
			questionRepo.save(questionA1_48);
			
			Question questionA1_56 = new Question(
					7, "Tại nơi đường bộ giao nhau cùng mức với đường sắt chỉ có đèn tín hiệu hoặc chuông báo hiệu," 
						+ " khi đèn tín hiệu màu đỏ đã bật sáng hoặc có tiếng chuông báo hiệu," 
						+ " người tham gia giao thông phải dừng lại ngay và giữ khoảng cách tối thiểu bao nhiêu mét tính từ ray gần nhất?",
					true, "", a1.getId(), set8.getId(), kn.getId());
			questionRepo.save(questionA1_56);
			
			Question questionA1_64 = new Question(
					8, "Khi điều khiển xe cơ giới, người lái xe phải bật đèn tín hiệu báo rẽ trong trường hợp nào sau đây?",
					true, "", a1.getId(), set8.getId(), kn.getId());
			questionRepo.save(questionA1_64);
			
			Question questionA1_72 = new Question(
					9, "Trên đường bộ (trừ đường cao tốc) trong khu vực đông dân cư, đường hai chiều hoặc đường một chiều có một làn xe cơ giới," 
						+ " loại xe nào dưới đây được tham gia giao thông với tốc độ tối đa cho phép là 50 km/h?",
					true, "", a1.getId(), set8.getId(), kn.getId());
			questionRepo.save(questionA1_72);
			
			Question questionA1_80 = new Question(
					10, "Tại nơi giao nhau, khi đèn điều khiển giao thông có tín hiệu màu vàng," 
						+ " người điều khiển giao thông phải chấp hành như thế nào là đúng quy tắc giao thông?",
					true, "", a1.getId(), set8.getId(), kn.getId());
			questionRepo.save(questionA1_80);
			
			Question questionA1_88 = new Question(
					11, "Trên đường đang xảy ra ùn tắc những hành vi nào sau đây là thiếu văn hóa khi tham gia giao thông?",
					true, "", a1.getId(), set8.getId(), vh.getId());
			questionRepo.save(questionA1_88);
			
			Question questionA1_96 = new Question(
					12, "Khi điều khiển xe mô tô quay đầu người lái xe cần thực hiện như thế nào để đảm bảo an toàn?",
					true, "", a1.getId(), set8.getId(), kt.getId());
			questionRepo.save(questionA1_96);
			
			Question questionA1_104 = new Question(
					13, "Biển nào cấm quay đầu xe?",
					true, "a1_104.jpg", a1.getId(), set8.getId(), bb.getId());
			questionRepo.save(questionA1_104);
			
			Question questionA1_112 = new Question(
					14, "Biển nào là biển \"Cấm đi ngược chiều\"?",
					true, "a1_112.jpg", a1.getId(), set8.getId(), bb.getId());
			questionRepo.save(questionA1_112);
			
			Question questionA1_120 = new Question(
					15, "Biển nào xe mô tô hai bánh được đi vào?",
					true, "a1_120.jpg", a1.getId(), set8.getId(), bb.getId());
			questionRepo.save(questionA1_120);
			
			Question questionA1_128 = new Question(
					16, "Biển nào báo hiệu \"Giao nhau với tín hiệu đèn\"?",
					true, "a1_128.jpg", a1.getId(), set8.getId(), bb.getId());
			questionRepo.save(questionA1_128);
			
			Question questionA1_136 = new Question(
					17, "Khi gặp biển nào, người lái xe phải giảm tốc độ, chú ý xe đi ngược chiều, xe đi ở phía đường bị hẹp phải nhường đường cho xe đi ngược chiều?",
					true, "a1_136.jpg", a1.getId(), set8.getId(), bb.getId());
			questionRepo.save(questionA1_136);
			
			Question questionA1_144 = new Question(
					18, "Gặp biển nào người tham gia giao thông phải đi chậm và thận trọng đề phòng khả năng xuất hiện và di chuyển bất ngờ của trẻ em trên mặt đường?",
					true, "a1_144.jpg", a1.getId(), set8.getId(), bb.getId());
			questionRepo.save(questionA1_144);
			
			Question questionA1_150 = new Question(
					19, "Trong các biển dưới đây biển nào là biển \"Hết tốc độ tối đa cho phép\"?",
					true, "a1_140.jpg", a1.getId(), set8.getId(), bb.getId());
			questionRepo.save(questionA1_150);
			
			Question questionA1_160 = new Question(
					20, "Vạch kẻ đường nào dưới đây là vạch phân chia các làn xe cùng chiều?",
					true, "a1_160.jpg", a1.getId(), set8.getId(), bb.getId());
			questionRepo.save(questionA1_160);
			
			Question questionA1_168 = new Question(
					21, "Trường hợp này xe nào được quyền đi trước?",
					true, "a1_168.jpg", a1.getId(), set8.getId(), sh.getId());
			questionRepo.save(questionA1_168);
			
			Question questionA1_176 = new Question(
					22, "Xe nào đỗ vi phạm quy tắc giao thông?",
					true, "a1_176.jpg", a1.getId(), set8.getId(), sh.getId());
			questionRepo.save(questionA1_176);
			
			Question questionA1_184 = new Question(
					23, "Trong hình dưới đây, xe nào chấp hành đúng quy tắc giao thông?",
					true, "a1_184.jpg", a1.getId(), set8.getId(), sh.getId());
			questionRepo.save(questionA1_184);
			
			Question questionA1_192 = new Question(
					24, "Các xe đi theo hướng mũi tên, xe nào vi phạm quy tắc giao thông?",
					true, "a1_192.jpg", a1.getId(), set8.getId(), sh.getId());
			questionRepo.save(questionA1_192);
			
			Question questionA1_200 = new Question(
					25, "Trong tình huống dưới đây," 
						+ " xa đầu kéo kéo rơ moóc (xe container) đang rẽ phải,"
						+ " xe con màu xanh và xe máy phía sau xe container đi như thế nào để đảm bảo an toàn?",
					true, "a1_200.jpg", a1.getId(), set8.getId(), sh.getId());
			questionRepo.save(questionA1_200);
		}
	}
	private void LoadAnswer() {
		// Đề 1
		List<Question> questionList = questionRepo.findAll();
		ArrayList<String> idList = new ArrayList<String>();
		
		for(Question q : questionList) {
			idList.add(q.getId());
		}
		
		if(answerRepo.count() == 0 && idList.size() > 0) {
			String[] answers_1 = {"Phần mặt đường và lề đường", "Phần đường xe chạy", "Phần đường xe cơ giới"};
			Answer answer_1 = new Answer(answers_1, 1, idList.get(0));
			answerRepo.save(answer_1);
		}
	}
}
