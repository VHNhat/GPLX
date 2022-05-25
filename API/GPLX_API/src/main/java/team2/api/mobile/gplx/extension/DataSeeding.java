package team2.api.mobile.gplx.extension;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import team2.api.mobile.gplx.models.Account;
import team2.api.mobile.gplx.models.AccountStatus;
import team2.api.mobile.gplx.models.License;
import team2.api.mobile.gplx.models.LicenseType;
import team2.api.mobile.gplx.models.QuestionType;
import team2.api.mobile.gplx.models.Role;
import team2.api.mobile.gplx.models.Status;
import team2.api.mobile.gplx.models.TrafficSign;
import team2.api.mobile.gplx.models.TrafficSignType;
import team2.api.mobile.gplx.repository.AccountRepository;
import team2.api.mobile.gplx.repository.LicenseRepository;
import team2.api.mobile.gplx.repository.LicenseTypeRepository;
import team2.api.mobile.gplx.repository.QuestionTypeRepository;
import team2.api.mobile.gplx.repository.RoleRepository;
import team2.api.mobile.gplx.repository.TrafficSignRepository;
import team2.api.mobile.gplx.repository.TrafficSignTypeRepository;

@Component
@AllArgsConstructor
public class DataSeeding implements CommandLineRunner {

	private LicenseTypeRepository licenseTypeRepo;
	private LicenseRepository licenseRepo;
	private RoleRepository roleRepo;
	private AccountRepository accountRepo;
	private TrafficSignTypeRepository trafficSignTypeRepo;
	private TrafficSignRepository trafficSignRepo;
	private QuestionTypeRepository questionTypeRepo;

	
	@Override
	public void run(String... args) throws Exception {
		LoadRole();
		LoadAccount();
		LoadLicenseType();
		LoadLicense();
		LoadTrafficSignType();
		LoadTrafficSign();
		LoadQuestionType();
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
			
			// Biển báo chỉ dẫn
			
			// Biển báo phụ
			
			// Vạch kẻ đường
		}
	}
	private void LoadQuestionType() {
		if(questionTypeRepo.count() == 0) {
			QuestionType conceptsAndRules = new QuestionType("KN", "Khái niệm và quy tắc giao thông", "Khái niệm và quy tắc giao thông");
			questionTypeRepo.save(conceptsAndRules);
			QuestionType cultureAndEthics = new QuestionType("VH", "Văn hóa và đạo đức", "Văn hóa và đạo đức");
			questionTypeRepo.save(cultureAndEthics);
			QuestionType roadSigns = new QuestionType("BB", "Biển báo đường bộ", "Biển báo đường bộ");
			questionTypeRepo.save(roadSigns);
			QuestionType pictures = new QuestionType("SH", "Sa hình", "Sa hình");
			questionTypeRepo.save(pictures);
		}
	}

}
