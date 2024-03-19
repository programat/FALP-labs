% family-tree.pl

% Family facts database
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

% Predicate men
% Lists all men in the database.
men :- man(X), write(X), nl, fail.

% Predicate women
% Lists all women in the database.
women :- woman(X), write(X), nl, fail.

% Predicate is_parent(+Parent:atom, +Child:atom)
% True if Parent is a parent of Child.
is_parent(Parent, Child) :- parent(Parent, Child), write(Parent), write(' is a parent of '), write(Child), nl, fail.

% Predicate children(+Parent:atom)
% Lists all children of Parent.
children(Parent) :- parent(Parent, Child), write(Child), nl, fail.

% Predicate mother(+Mother:atom, +Child:atom)
% True if Mother is the mother of Child.
mother(Mother, Child) :- woman(Mother), parent(Mother, Child).

% Predicate mother(?Child:atom)
% Lists mother of Child.
mother(Child) :- woman(Mother), parent(Mother, Child), write(Mother), fail.

% Predicate brother(+Brother:atom, +Sibling:atom)
% True if Brother is a brother of Sibling.
brother(Brother, Sibling) :- man(Brother), parent(Parent, Brother), parent(Parent, Sibling), Brother \= Sibling.

% Predicate brother(+Sibling:atom)
% Lists all brothers of Sibling.
brother(Sibling) :- man(Brother), brother(Brother, Sibling), write(Brother), nl.

% Predicate b_s(+Sibling1:atom, +Sibling2:atom)
% True if Sibling1 and Sibling2 are full siblings.
b_s(Sibling1, Sibling2) :- parent(Parent, Sibling1), parent(Parent, Sibling2), Sibling1 \= Sibling2.

% Predicate b_s(+Sibling:atom)
% Lists all full siblings of Sibling.
b_s(Sibling) :- b_s(Sibling, Sibling2), write(Sibling2), nl.

% Task 2.6 ---

% Predicate daughter(+Daughter:atom, +Parent:atom)
% True if Daughter is a daughter of Parent.
daughter(Daughter, Parent) :- woman(Daughter), parent(Parent, Daughter).

% Predicate daughter(+Parent:atom)
% Lists all daughters of Parent.
daughter(Parent) :- woman(Daughter), parent(Parent, Daughter), write(Daughter), nl, fail.

% Predicate husband(+Husband:atom, +Wife:atom)
% True if Husband is the husband of Wife.
husband(Husband, Wife) :- man(Husband), parent(Husband, Child), parent(Wife, Child), Husband \= Wife.

% Predicate husband(+Wife:atom)
% Lists all husbands of Wife.
husband(Wife) :- man(Husband), parent(Husband, Child), parent(Wife, Child), Husband \= Wife, write(Husband).

% Task 3.6 ---

% Predicate grand_ma(+Grandma:atom, +Grandchild:atom)
% True if Grandma is the grandmother of Grandchild.
grand_ma(Grandma, Grandchild) :- parent(Grandma, Parent), parent(Parent, Grandchild), woman(Grandma).

% Predicate grand_ma1(+Grandma:atom, +Grandchild:atom)
% True if Grandma is the grandmother of Grandchild using predefined predicates.
grand_ma1(Grandma, Grandchild) :- mother(Grandma, Parent), parent(Parent, Grandchild).

% Predicate grand_mas(+Grandchild:atom)
% Lists all grandmothers of Grandchild.
grand_mas(Grandchild) :- grand_ma(Grandma, Grandchild), write(Grandma), nl, fail.

% Predicate grand_ma_and_da(+Grandparent:atom, +Grandchild:atom)
% True if Grandparent is the grandmother or grandfather of Grandchild (can be used as grand_ma_and_da(Grandchild, Grandparent)).
grand_ma_and_da(Grandparent, Grandchild) :- woman(Grandchild), (grand_ma(Grandparent, Grandchild); grand_ma(Grandchild, Grandparent)).

% Predicate sister(+Sister:atom, +Sibling:atom)
% True if Sister is a sister of Sibling.
sister(Sister, Sibling) :- woman(Sister), b_s(Sister, Sibling).

% Predicate aunt(+Aunt:atom, +NieceNephew:atom)
% True if Aunt is the aunt of NieceNephew.
aunt(Aunt, NieceNephew) :- woman(Aunt), parent(Parent, NieceNephew), sister(Aunt, Parent).

% Predicate aunts1(+NieceNephew:atom)
% Lists all aunts of NieceNephew (with repetitions).
aunts1(NieceNephew) :- aunt(Aunt, NieceNephew), write(Aunt), nl, fail.

% Predicate aunts(+NieceNephew:atom)
% Lists all unique aunts of NieceNephew.
aunts(NieceNephew) :- findall(Aunt, aunt(Aunt, NieceNephew), Aunts), list_to_set(Aunts, UniqueAunts), member(Aunt, UniqueAunts), write(Aunt), nl, fail.
