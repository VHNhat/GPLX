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
