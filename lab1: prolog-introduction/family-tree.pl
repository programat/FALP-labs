% family-tree.pl

% Факты о членах семьи
man(voeneg).
man(ratibor).
man(boguslav).
man(velerad).
man(duhovlad).
man(svyatoslav).
man(dobrozhir).
man(bogomil).
man(zlatomir).

woman(goluba).
woman(lubomila).
woman(bratislava).
woman(veslava).
woman(zhdana).
woman(bozhedara).
woman(broneslava).
woman(veselina).
woman(zdislava).

parent(voeneg,ratibor).
parent(voeneg,bratislava).
parent(voeneg,velerad).
parent(voeneg,zhdana).

parent(goluba,ratibor).
parent(goluba,bratislava).
parent(goluba,velerad).
parent(goluba,zhdana).

parent(ratibor,svyatoslav).
parent(ratibor,dobrozhir).
parent(lubomila,svyatoslav).
parent(lubomila,dobrozhir).

parent(boguslav,bogomil).
parent(boguslav,bozhedara).
parent(bratislava,bogomil).
parent(bratislava,bozhedara).

parent(velerad,broneslava).
parent(velerad,veselina).
parent(veslava,broneslava).
parent(veslava,veselina).

parent(duhovlad,zdislava).
parent(duhovlad,zlatomir).
parent(zhdana,zdislava).
parent(zhdana,zlatomir).

% Педикат men
men :- man(X), write(X), nl, fail.

% Педикат women
women :- woman(X), write(X), nl, fail.

% Предикат "является родителем" X - родитель Y
is_parent(X, Y) :- parent(X, Y), write(X), write(' is a parent of '), write(Y), nl, fail.

% Предикат children(X) для вывода всех детей X
children(X) :- parent(X, Y), write(Y), nl, fail.

% Предикат mother(X, Y):   X - мать Y?
mother(X, Y) :- woman(X), parent(X, Y).

% Предикат mother(X)  выводит маму X
mother(X) :- woman(Y), parent(Y, X), write(Y), fail.

% Предикат brother(X, Y):   X - брат Y?
brother(X, Y) :- man(X), parent(P, X), parent(P, Y), X \= Y.

% Предикат brother(X) для вывода всех братьев X
brother(X) :- man(Y), brother(Y, X), write(Y), nl.

% Предикат b_s(X,Y): X и Y - родные брат и сестра (сестра и брат / сестра и сестра / брат и брат)?
b_s(X, Y) :- parent(P, X), parent(P, Y), X \= Y.

% Предикат b_s(X) для вывода всех братьев или сестер X
b_s(X) :- b_s(X, Y), write(Y), nl.


% 2.6
% Предикат daughter(X, Y):  X - дочь Y?
daughter(X, Y) :- woman(X), parent(Y, X).
% Предикат brother(X) для вывода дочери X
daughter(X) :- woman(Y), parent(X, Y), write(Y), fail.

% Предикат husband(X, Y):  X - муж Y?
husband(X, Y) :- man(X), parent(X, C), parent(Y, C), X \= Y.
% Предикат brother(X) для вывода мужа X
husband(X) :- man(Y), parent(X, C), parent(Y, C), X \= Y, write(Y).

% 3.6
% Предикат grand_ma(X, Y): X - бабушка Y?
grand_ma(X, Y) :- parent(X, P), parent(P, Y), woman(X).
% Предикат grand_ma1(X, Y): X - бабушка Y? с использованием готовых предиктатов
grand_ma1(X, Y) :- mother(X, P), parent(P, Y).

% Предикат grand_mas(X) для вывода всех бабушек X
grand_mas(X) :- grand_ma(Y, X), write(Y), nl, fail.

% Предикат grand_ma_and_da(X, Y): X - бабушка и Y - внучка (или наоборот)?
grand_ma_and_da(X, Y) :- woman(Y), woman(X), (grand_ma(X, Y); grand_ma(Y, X)).


% Предикат sister(X, Y): X - сестра Y?
sister(X, Y) :- woman(X), b_s(X, Y).
% Предикат aunt(X, Y): X - тетя Y?
aunt(X, Y) :- woman(X), parent(P, Y), sister(X, P).

% Предикат aunts1(X): выводит всех тетей X с повторениями :(
aunts1(X) :- aunt(Y, X), write(Y), nl, fail.

% Предикат aunts(X): выводит всех тетей X
aunts(X) :- findall(Y, aunt(Y, X), Aunts), list_to_set(Aunts, UniqueAunts), member(Y, UniqueAunts), write(Y), nl, fail.

