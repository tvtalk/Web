�ٵ� ������ ������ �� ���̳���?
�����涧 �ʹ� ���� ¥���� Crawling�κ��� ���� �պý��ϴ�.
API�ּ��� ���� ÷���� ������ Ȯ���Ͻð� ����Ͻø� �� �� ���׿�.

local���� DB�� ����ؼ� �׽�Ʈ�غ��� �����źе��� ���ؼ� ScheduleReservationDTO.java������ toString�޼ҵ带 insert����ó�� �����س����ϴ�.
�׳� ��ü�� �����ø� ������ ���ɴϴ�.


DB DDL�Դϴ�.
create table schedule_reservation(sr int auto_increment primary key , thumbnail varchar(1000) not null , title varchar(200) not null , broadcast_brand varchar(200) ,broadcasting_time varchar(200), broadcast_day varchar(100), genre varchar(100) not null,rating float );
create table user(nickname varchar(100) primary key);
create table bookmark (bk int auto_increment primary key , sr int references schedule_reservation(sr) , nickname varchar(100) references user(nickname) , title varchar(200) references schedule_reservation(title));
create table link(url varchar(255) unique);

Ȥ�� ȭ�鱸���� �����ϽŴٴ��� Ư���ϰ� ����� �κ� �����ø� �Ѱ��ּ���. �����ؼ� �ٽ� �帱�Կ�
