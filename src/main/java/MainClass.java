import dao.TbCharDao;
import dto.TbChar;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MainClass {
    // 게임 시작
    // 1. 게임 설정 2. 플레이 게임 3. 저장된 정보 보기
    // 만약에 1번이면
    // 캐릭터 정보 설정
    // 캐릭터 정보가 디비에 저장
    // 몬스터 정보 설정
    // 몬스터 정보 디비에 저장
    // 아이템 정보 설정
    // 아이템 정보 디비에 저장
    public static void main(String[] args) {
        System.out.println("게임 시작");
        // 여기서 반복하고 싶은데
        for (;;) {
            System.out.println("1. 게임 설정 2. 플레이 게임 3. 저장된 정보 보기");
            System.out.println("종료는 1~3 이외의 번호를 누르시오");
            // 유저에게 입력을 받아야 되네
            Scanner sc = new Scanner(System.in);
            System.out.print("입력: ");
            int num = sc.nextInt();
            // 만약에 1번이면
            if (num == 1) {
                System.out.println("게임설정 합니다");
                System.out.println("케릭터 설정");
                System.out.print("이름 입력: ");
                String name = sc.next();
                System.out.print("hp 입력: ");
                int  hp = sc.nextInt();
                System.out.print("직업 입력: ");
                String job = sc.next();
                System.out.println(name + " 입력하셨습니다");
                // 데이터 설정
                TbChar dto = new TbChar();
                dto.setName(name);
                dto.setHp(hp);
                dto.setJob(job);
                TbCharDao dao = new TbCharDao();
                dao.insert(dto);
            } else if (num == 2) {
                System.out.println("게임을 시작합니다");
                // 만약에 저장된 데이터가 없으면 데이터 설정하라고 하는 메세지
                TbCharDao dao = new TbCharDao();
                ArrayList<TbChar> list = dao.select();  // 여기서 데이터를 가지고 와서
                if (list.isEmpty()) {
                    System.out.println("캐릭터 정보가 없습니다. 게임 설정을 하세요");
                } else {
                    for (TbChar tb : list) {
                        System.out.println(tb.getName());
                        System.out.println("정보가 있습니다.");
                        System.out.println("사용하고 싶은 플레이어를 입력하세요");
                        String name = sc.next();
                        TbChar dto = dao.select(name);
                        System.out.println("선택하신 플레이어 정보입니다.");
                        System.out.println("이름: " + dto.getName());
                        System.out.println("HP: " + dto.getHp());
                        System.out.println("직업: " + dto.getJob());
                        System.out.println("게임을 시작합니다");
                        System.out.println("퉁퉁이와 싸웁니다");
                        System.out.println("퉁퉁이가 공격을 했습니다");
                        int hp = dto.getHp();
                        Random rnd = new Random();
                        int r = rnd.nextInt(100);
                        hp = hp - r;
                        System.out.println("내 HP가 " + hp + "가 남았습니다");
                        System.out.println("게임을 중단 할까요?");
                        System.out.println("1. 중단 2. 계속");
                        System.out.println("데이터를 저장합니다.");
                        dao.update(hp, dto.getId());

                    }
                }
            } else if (num == 3) {
                System.out.println("정보 보기");
                TbCharDao dao = new TbCharDao();
                ArrayList<TbChar> list = dao.select();  // 여기서 데이터를 가지고 와서
                // 여기에 출력 하고 싶다
                for (TbChar tb : list) {
                    System.out.println(tb.getName());
                    System.out.println(tb.getHp());
                    System.out.println(tb.getJob());
                }

            } else {
                System.out.println("게임 종료");
                break;
            }
        }
    }

    // 만약 2번이면 게임이 시작 된다.
    // 디비에 저장된 정보가 있으면 게임 시작(이전에 만든 시나리오 알아서)
    // 디비에 저장된 정보가 없으면 게임 설정으로 가라고 안내 메세지
    // 만약에 3번이면
    // 1. 캐릭터 정보 보기 2. 몬스터 정보 보기 3. 아이템 정보 보기
    // 각각 디비에 저장되어 있는 정보들을 출력
}
