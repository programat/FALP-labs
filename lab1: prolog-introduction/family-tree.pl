% family-tree.pl

% Факты о членах семьи
man(john).
man(mike).
man(jack).
man(joe).
man(bob).
man(jim).
man(tom).
man(peter).

woman(mary).
woman(susan).
woman(lisa).
woman(emma).
woman(anne).
woman(aurora).
woman(isabella).
woman(kate).

% Отношения "родитель"
parent(john, mary).
parent(john, anne).
parent(isabella, mary).
parent(isabella, anne).

parent(john, kate).
parent(isabella, kate).

parent(mary, susan).
parent(mary, lisa).
parent(tom, susan).
parent(tom, lisa).

parent(mike, joe).
parent(mike, jack).
parent(anne, joe).
parent(anne, jack).

parent(joe, bob).
parent(joe, jim).
parent(aurora, bob).
parent(aurora, jim).

parent(lisa, emma).
parent(peter, emma).


%                   John & Isabella
%                      /  |  \
%                     /   |   \
%           Tom & Mary  Kate   Anne & Mike
%              / \                 | \
%         Susan   Lisa & Peter  Jack  Joe & Aurora
%                     /                 /  \
%                   Emma              Bob   Jim


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


% 1.2.6
% Предикат daughter(X, Y):  X - дочь Y?
daughter(X, Y) :- woman(X), parent(Y, X).
% Предикат brother(X) для вывода дочери X
daughter(X) :- woman(Y), parent(X, Y), write(Y), fail.

% Предикат husband(X, Y):  X - муж Y?
husband(X, Y) :- man(X), parent(X, C), parent(Y, C), X \= Y.
% Предикат brother(X) для вывода мужа X
husband(X) :- man(Y), parent(X, C), parent(Y, C), X \= Y, write(Y).

% 1.3.6
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

