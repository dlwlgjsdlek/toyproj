package project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main4 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int menu = 0; // 메뉴
		int menu1 = 0; // 게임 종료 후 메뉴
		int n = 0;	// 말의 숫자
		int m = 0;	// 경기장 길이
		int w = 0; // 배팅하려는 말 선택기
		int account = 0; // 잔액
		int betting = 0; // 배팅금액
		int money = 0; // 충전하려는 금액
		int profit = 0; // 배당금
		while (true) {
			while (true) {
				try { // 메뉴 입력
					System.out.println("=====================");
					System.out.println("경마게임에 오신 것을 환영합니다.");
					System.out.println("1. 게임 시작");
					System.out.println("2. 잔액 충전");
					System.out.println("3. 잔액 조회");
					System.out.println("4. 종료");
					System.out.print(">> ");

					menu = Integer.parseInt(sc.nextLine());

					if (!(menu == 1) && !(menu == 2) && !(menu == 3) && !(menu == 4)) {
						System.out.println("메뉴 입력범위를 벗어났습니다.");
						continue;
					}

				} catch (Exception e) { // 메뉴 입력 예외처리
					System.out.println("숫자를 입력해주세요.");
					continue;
				}
				break;
			}
			switch (menu) {
			// 1번메뉴 게임시작
			case 1:
				// 잔액이 0원인 경우, 메뉴로 돌아가기
				if (account == 0) {
					System.out.println("잔액이 0원입니다. 잔액을 충전해주세요.");
					break;
				}
				System.out.print("참여할 말의 숫자를 입력하세요 : ");
				try {
				n = Integer.parseInt(sc.nextLine());
				}catch (Exception e) { // 메뉴 입력 예외처리
					System.out.println("숫자를 입력해주세요.");
					continue;
				}
				if (n == 0) {
					break;
				} else {
					// 경기장 길이
					try {
					System.out.print("경기장의 길이를 입력하세요 : ");
					m = Integer.parseInt(sc.nextLine());
					}catch (Exception e) { // 메뉴 입력 예외처리
						System.out.println("숫자를 입력해주세요.");
						continue;
					}
					if (m == 0) {
						break;
					} else {
						// 말 이름
						List<Horse2> Hlist = new ArrayList<Horse2>();
						for (int i = 1; i <= n; i++) {
							Horse2 h = new Horse2(i);
							Hlist.add(h);
						}
						for (int i = 0; i < Hlist.size(); i++) {
							Horse2 item = Hlist.get(i);
							System.out.println(item.toString());
						}
						// 배팅할 말 선택
						
						while (true) {
							System.out.print("1등으로 예상하는 말의 번호를 입력해주세요 : ");
							try {
								int num = 0;
								num = Integer.parseInt(sc.nextLine());
								if (num <= n) {
									w = num;
									System.out.println();
									System.out.println(w + "번마를 선택하셨습니다.");
									
								} else {
									System.out.println(n + "이하의 수를 입력해 주세요.");

									continue;
								}
							} catch (Exception e) {
								System.out.println("입력값이 숫자가 아닙니다.");
								continue;
							}
							break;
						}
						
						// 배팅할 말 종료

						// 배팅금액 입력
						while (true) {
							try {
								System.out.print("얼마를 배팅하겠습니까? : ");
								betting = Integer.parseInt(sc.nextLine());
								if (betting > account) {
									System.out.println("잔액보다 배팅금액이 커서, 배팅이 불가능합니다.");
									continue;
								} else {
									account -= betting;
								}
							} catch (Exception e) {
								System.out.println("입력값이 숫자가 아닙니다.");
								continue;
							}
							break;
							// 배팅금액 입력 종료
						}
						System.out.println("=============게임 시작=============");
						for (Horse2 horse : Hlist) {
							horse.start();
						}
						// 게임이 시작하기전 잠시 멈춤
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

						String[] arr = new String[m];
						int rnk = 1;
						boolean ing = true;
						while (ing) {

							for (Horse2 h1 : Hlist) {
								if (h1.isGoal() == true) {
									System.out.print(h1.getHName() + "번마 : ");
									for (int j = 0; j < m; j++) {
										arr[j] = "*";
										System.out.print(arr[j]);
									}
									System.out.println();
									continue;
								}

								System.out.print(h1.getHName() + "번마 : ");
								for (int i = 0; i < m; i++) {
									arr[i] = "-";
									if (h1.getLocation() == i) {
										arr[i] = "🐴";
									}
								}

								for (int j = 0; j < m; j++) {
									System.out.print(arr[j]);
								}
								System.out.println();

								if (h1.getLocation() >= m) {
									h1.setRank(rnk);
									rnk++;
									h1.setGoal(true);
								}
							}

							try {
								Thread.sleep(200);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							System.out.println("=================================");

							if (rnk == n + 1) {

								System.out.println("==============경기 끝==============");
								ing = false;
							}
						}
						// 경기결과 출력
						
						Collections.sort(Hlist);
						// 말 등수
						for (Horse2 h1 : Hlist) {
							System.out.printf("%3d등 : %3s번마", h1.getRank(), h1.getHName());
							System.out.println();
						}
//				
						for (Horse2 h1 : Hlist) {
							if (h1.getRank() == 1 && w == h1.getHName()) {
								
								System.out.println(w + "번마가 우승하였습니다.");
								System.out.println("배팅금액 : " + betting);
								profit = betting * n;
								System.out.println("배당금 : " + profit);
								account += profit;
								System.out.println("현재 잔액은 " + account + "원입니다.");

							}
							if (h1.getRank() != 1 && w == h1.getHName()) {
								System.out.println(w + "번마가 우승하지 못했습니다.");
								System.out.println("현재 잔액은 " + account + "원입니다.");
							}
							
						}
					}
					
				}
				while (true) {
					try { // 메뉴 입력
						System.out.println("=====================");
						System.out.println("1. 메뉴로 이동");
						System.out.println("2. 종료");
						System.out.print(">> ");

						menu1 = Integer.parseInt(sc.nextLine());

						if (!(menu1 == 1) && !(menu1 == 2) ) {
							System.out.println("메뉴 입력범위를 벗어났습니다.");
							continue;
						}

					} catch (Exception e) { // 메뉴 입력 예외처리
						System.out.println("숫자를 입력해주세요.");
						continue;
					}
					break;
				}
				switch (menu1) {
				case 1: 
					break;
				
				case 2:
					System.out.println("경마게임을 종료합니다.");
					System.out.println("===============");
					System.exit(0);
				}
				break;
			// 1번메뉴 게임종료

			// 2번메뉴 잔액충전 시작
			case 2:
				// 충전금액 입력
				while (true) {
					try {
						System.out.println("얼마를 충전하시겠습니까?");
						money = Integer.parseInt(sc.nextLine());
						System.out.println(money + "원이 충전되었습니다.");
						account += money;
					} catch (Exception e) {
						System.out.println("입력값이 숫자가 아닙니다.");
					}
					break;
				}
				break;

			// 2번메뉴 잔액충전 종료

			// 3번메뉴 잔액조회 시작
			case 3:
				System.out.println("현재 잔액은 " + account + "원입니다.");

				break;
			// 3번메뉴 잔액조회 종료

			// 4번메뉴 게임종료기능 시작
			case 4:
				System.out.println("경마게임을 종료합니다.");
				System.out.println("===============");
				System.exit(0);
				// 4번메뉴 게임종료기능 종료
			}
			// 메뉴별 기능 종료

		}
		// 경마게임종료
	}

}
