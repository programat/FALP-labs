% list-predicates.pl

% Task 1 ---

% read_list(-List:list)
% Reads a list of elements from the user input until 'q' is entered.
read_list(List) :-
    read(X),
    (X == q -> List = [] ; List = [X|T], read_list(T)).

% write_list(+List:list)
% Writes the elements of the list, separated by commas.
write_list([]).
write_list([H|T]) :-
    write(H),
    (   T == []
    ->  true
    ;   write(', '),
        write_list(T)
    ).

% sum_list_down(+List:list, -Summ:number)
% Calculates the sum of elements in the list using recursive ascent.
sum_list_down([], 0).
sum_list_down([H|T], Summ) :-
    sum_list_down(T, Summ1),
    Summ is H + Summ1.

% sum_list_program
% Reads a list from the user, calculates its sum, and writes the list and sum.
sum_list_program :-
    write('Enter the list (end with "q."): '), nl,
    read_list(List),
    sum_list_down(List, Summ),
    write_list(List), nl,
    write('The sum of the list is: '), write(Summ), nl.

% sum_list_up(+List:list, +Acc:number, -Summ:number)
% Calculates the sum of elements in the list using an accumulator-based recursion (tail recursion).
sum_list_up([], Summ, Summ).
sum_list_up([H|T], Acc, Summ) :-
    Acc1 is H + Acc,
    sum_list_up(T, Acc1, Summ).

% sum_list_up(+List:list, -Summ:number)
% Wrapper predicate for sum_list_up/3 with initial accumulator value of 0.
sum_list_up(List, Summ) :- sum_list_up(List, 0, Summ).

% sum_digits(+N:integer, -Summ:integer)
% Calculates the sum of digits of a non-negative integer N.
sum_digits(0, 0).
sum_digits(N, Summ) :-
    N > 0,
    Digit is N mod 10,
    N1 is N div 10,
    sum_digits(N1, Summ1),
    Summ is Digit + Summ1.

% remove_elements(+List:list, +Summ:integer, -Result:list)
% Removes elements from the list whose sum of digits equals Summ.
remove_elements([], _, []).
remove_elements([H|T], Summ, Result) :-
    sum_digits(H, DigitSum),
    (DigitSum =:= Summ ->
        remove_elements(T, Summ, Result)
    ;
        remove_elements(T, Summ, Result1),
        Result = [H|Result1]
    ).


% Task 3 --- 1.7, 1.18, 1.27

% 1.7
% shift_right(+List:list, -Shifted:list)
% Shifts the last two elements of List to the beginning of Shifted.
shift_right(List, Shifted) :-
    append(Left, Right, List),
    length(Right, 2),
    append(Right, Left, Shifted).

% shift_right
% Reads a list from the user, shifts the last two elements to the beginning, and writes the shifted list.
shift_right :-
    write('Enter the list (end with "q."): '), nl,
    read_list(List),
    shift_right(List, Shifted),
    write('Shifted list: '), write_list(Shifted), nl.

% 1.18
% min_list(+List:list, -Min:number)
% Finds the minimum element in List.
min_list([Min], Min).
min_list([H|T], Min) :-
    min_list(T, MinT),
    Min is min(H, MinT).

% before_min(+List:list, -Before:list)
% Finds the sublist of elements before the minimum element in List.
before_min(List, Before) :-
    min_list(List, Min),
    append(Before, [Min|_], List).

% before_min
% Reads a list from the user, finds the list of elements before the minimum element, and writes the result.
before_min:-
    write('Enter the list (end with "q."): '), nl,
    read_list(List),
    before_min(List, Before),
    write('List of elements before min: '), write_list(Before), nl.

% 1.27
% shift_left(+List:list, -Shifted:list)
% Shifts the first element of List to the end of Shifted.
shift_left(List, Shifted) :-
    append(Left, Right, List),
    length(Left, 1),
    append(Right, Left, Shifted).

% shift_left
% Reads a list from the user, shifts the first element to the end, and writes the shifted list.
shift_left :-
    write('Enter the list (end with "q."): '), nl,
    read_list(List),
    shift_left(List, Shifted),
    write('Shifted list: '), write_list(Shifted), nl.

