다들 유니톤 끝나고 잘 쉬셨나요?
유니톤때 너무 대충 짜놓은 Crawling부분을 조금 손봤습니다.
API주석을 같이 첨부해 놨으니 확인하시고 사용하시면 될 것 같네요.

local에서 DB를 사용해서 테스트해보고 싶으신분들을 위해서 ScheduleReservationDTO.java파일의 toString메소드를 insert구문처럼 변경해놨습니다.
그냥 객체를 찍으시면 쿼리가 나옵니다.


DB DDL입니다.
create table schedule_reservation(sr int auto_increment primary key , thumbnail varchar(1000) not null , title varchar(200) not null , broadcast_brand varchar(200) ,broadcasting_time varchar(200), broadcast_day varchar(100), genre varchar(100) not null,rating float );
create table user(nickname varchar(100) primary key);
create table bookmark (bk int auto_increment primary key , sr int references schedule_reservation(sr) , nickname varchar(100) references user(nickname) , title varchar(200) references schedule_reservation(title));
create table link(url varchar(255) unique);

혹시 화면구성을 변경하신다던지 특정하게 변경된 부분 있으시면 넘겨주세요. 수정해서 다시 드릴게요
