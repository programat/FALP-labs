% numeric-predicates.pl

% Task 1 ---

% max(+X:number, +Y:number, +U:number, -Z:number)
% True if Z is the atom representing the maximum of X, Y, and U.
max(X, Y, U, Z) :-
    U >= X,
    U >= Y,
    Z = U.

% max_for_normal_people(+X:number, +Y:number, +U:number, -Z:number)
% True if Z is the maximum of X, Y, and U.
max_for_normal_people(X, Y, U, U) :-
    U >= X,
    U >= Y, !.
% Second clause: If Y is greater than or equal to X, Z is unified with Y.
max_for_normal_people(X, Y, _, Y) :- Y >= X.
% Third clause: If the previous clauses fail, Z is unified with X.
max_for_normal_people(X, _, _, X).


% fact_up(+N:integer, -X:integer)
% Calculates the factorial of N using recursive ascent.
% Base case: factorial of 0 is 1.
fact_up(0, 1).

% Recursive case: factorial of N > 0 is N * factorial(N-1).
fact_up(N, X) :-
    N > 0,
    N1 is N - 1,
    fact_up(N1, X1),
    X is N * X1.

% fact_down(+N:integer, +Acc:integer, -X:integer)
% Calculates the factorial of N using accumulator-based recursion (tail recursion).
% Base case: if N is 0, unify X with the accumulator Acc.
fact_down(0, X, X).


% Recursive case: if N > 0, calculate factorial(N) using the accumulator.
fact_down(N, Acc, X) :-
    N > 0,
    Acc1 is Acc * N,
    N1 is N - 1,
    fact_down(N1, Acc1, X).

% call_fact_down(+N:integer, -X:integer)
% Wrapper predicate to calculate factorial of N using fact_down/3.
% Calls fact_down/3 with initial accumulator value of 1.
call_fact_down(N, X) :- fact_down(N, 1, X).


% sum_digits_up(+N:integer, -S:integer)
% Calculates the sum of digits of N using recursive ascent.
% Base case: sum of digits of 0 is 0.
sum_digits_up(0, 0).

% Recursive case: sum of digits of N > 0.
sum_digits_up(N, S) :-
    N > 0,
    D is N mod 10,
    N1 is N div 10,
    sum_digits_up(N1, S1),
    S is D + S1.


% sum_digits_down(+N:integer, +Acc:integer, -S:integer)
% Calculates the sum of digits of N using accumulator-based recursion (tail recursion).
% Base case: if N is 0, unify S with the accumulator Acc.
sum_digits_down(0, S, S).

% Recursive case: if N > 0, calculate sum of digits using the accumulator.
sum_digits_down(N, Acc, S) :-
    N > 0,
    D is N mod 10,
    Acc1 is Acc + D,
    N1 is N div 10,
    sum_digits_down(N1, Acc1, S).

% sum_digits(+N:integer, -S:integer)
% Wrapper predicate to calculate the sum of digits of N.
% Calls sum_digits_down/3 with initial accumulator value of 0.
sum_digits(N, S) :- sum_digits_down(N, 0, S).


% square_free(+N:integer)
% Checks if N is a square-free number (not divisible by any perfect square other than 1).
% Base case: 1 is considered square-free.
square_free(1).

% Recursive case: N > 1 is square-free if it doesn't have any square factors.
square_free(N) :-
    N > 1,
    not(has_square_factor(N, 2)).

% has_square_factor(+N:integer, +F:integer)
% Checks if N has any square factors starting from F.
has_square_factor(N, F) :-
    F * F =< N,
    (
        % If N is divisible by F^2, then it has a square factor.
        0 is N mod (F * F)
    ;
        % Otherwise, recursively check for the next potential factor.
        F1 is F + 1,
        has_square_factor(N, F1)
    ).


% Task 2.7 ---

% product_digits_up(+N:integer, -P:integer)
% Calculates the product of digits of N using recursive ascent.
% Base case: product of digits of 0 is 1.
product_digits_up(0, 1).

% Recursive case: product of digits of N > 0.
product_digits_up(N, P) :-
    N > 0,
    D is N mod 10,
    N1 is N div 10,
    product_digits_up(N1, P1),
    P is D * P1.

% product_digits_down(+N:integer, +Acc:integer, -P:integer)
% Calculates the product of digits of N using accumulator-based recursion (tail recursion).
% Base case: if N is 0, unify P with the accumulator Acc.
product_digits_down(0, P, P).

% Recursive case: if N > 0, calculate the product of digits using the accumulator.
product_digits_down(N, Acc, P) :-
    N > 0,
    D is N mod 10,
    Acc1 is Acc * D,
    N1 is N div 10,
    product_digits_down(N1, Acc1, P).

% product_digits(+N:integer, -P:integer)
% Wrapper predicate to calculate the product of digits of N.
% Calls product_digits_down/3 with initial accumulator value of 1.
product_digits(N, P) :- product_digits_down(N, 1, P).


% odd_gt3(+D:integer)
% True if D is an odd number greater than 3.
odd_gt3(D) :- D > 3, 1 is D mod 2.

% count_odd_gt3_up(+N:integer, -C:integer)
% Counts the number of odd digits greater than 3 in N using recursive ascent.
% Base case: count is 0 if N is 0.
count_odd_gt3_up(0, 0).

% Recursive case: count odd digits greater than 3 in N.
count_odd_gt3_up(N, C) :-
    N > 0,
    D is N mod 10,
    N1 is N div 10,
    count_odd_gt3_up(N1, C1),
    (odd_gt3(D) -> C is C1 + 1 ; C = C1).

% count_odd_gt3_down(+N:integer, +Acc:integer, -C:integer)
% Counts the number of odd digits greater than 3 in N using accumulator-based recursion (tail recursion).
% Base case: if N is 0, unify C with the accumulator Acc.
count_odd_gt3_down(0, C, C).

% Recursive case: if N > 0, count odd digits greater than 3 using the accumulator.
count_odd_gt3_down(N, Acc, C) :-
    N > 0,
    D is N mod 10,
    (odd_gt3(D) -> Acc1 is Acc + 1 ; Acc1 = Acc),
    N1 is N div 10,
    count_odd_gt3_down(N1, Acc1, C).

% count_odd_gt3(+N:integer, -C:integer)
% Wrapper predicate to count the number of odd digits greater than 3 in N.
% Calls count_odd_gt3_down/3 with initial accumulator value of 0.
count_odd_gt3(N, C) :- count_odd_gt3_down(N, 0, C).

% gcd_up(+X:integer, +Y:integer, -G:integer)
% Calculates the greatest common divisor (GCD) of X and Y using recursive ascent.
% Base case: if Y is 0, the GCD is X (assuming X >= 0).
gcd_up(X, 0, X) :- X >= 0.

% Recursive case: calculate GCD using Euclid's algorithm.
gcd_up(X, Y, G) :-
    Y > 0,
    R is X mod Y,
    gcd_up(Y, R, G).

% gcd_down(+X:integer, +Y:integer, +_:integer, -G:integer)
% Calculates the GCD of X and Y using accumulator-based recursion (tail recursion).
% Base case: if Y is 0, unify G with X (assuming X >= 0).
gcd_down(X, 0, X, X) :- X >= 0.

% Recursive case: calculate GCD using Euclid's algorithm.
gcd_down(X, Y, _, G) :-
    Y > 0,
    R is X mod Y,
    gcd_down(Y, R, Y, G).

% gcd(+X:integer, +Y:integer, -G:integer)
% Wrapper predicate to calculate the GCD of X and Y.
% Calls gcd_down/4 with initial accumulator value of 0.
gcd(X, Y, G) :- gcd_down(X, Y, 0, G).


% Task 5.7 ---

% read_number(-Number:integer)
% Prompts the user to enter a number and reads it.
read_number(Number) :-
    write('Enter a number: '),
    read(Number).

% divisor_product(+Number:integer, -Product:integer)
% Calculates the product of divisors of Number whose digit sum is less than the digit sum of Number.
divisor_product(Number, Product) :-
    sum_digits(Number, DigitSum),
    divisor_product_acc(Number, 1, Number, DigitSum, Product).

% divisor_product_acc(+Divisor:integer, +Acc:integer, +Number:integer, +DigitSum:integer, -Product:integer)
% Recursive helper predicate for divisor_product/2.
% Base case: if Divisor is 1, unify Product with the accumulator Acc.
divisor_product_acc(1, Acc, _, _, Acc).

% Recursive case: check if Divisor divides Number and its digit sum is less than DigitSum.
% If so, multiply Acc by Divisor to get the new accumulator value.
divisor_product_acc(Divisor, Acc, Number, DigitSum, Product) :-
    Divisor > 1,
    (Number mod Divisor =:= 0 ->
        sum_digits(Divisor, DivisorDigitSum),
        (DivisorDigitSum < DigitSum ->
            NewAcc is Acc * Divisor
        ;
            NewAcc = Acc
        )
    ;
        NewAcc = Acc
    ),
    NextDivisor is Divisor - 1,
    divisor_product_acc(NextDivisor, NewAcc, Number, DigitSum, Product).

% write_result(+Product:integer)
% Writes the result (product of divisors) to the console.
write_result(Product) :-
    write('The product of divisors with digit sum less than the original number is: '),
    write(Product), nl.

% task5
% Main predicate that reads a number, calculates the product of divisors, and writes the result.
task5 :-
    read_number(Number),
    divisor_product(Number, Product),
    write_result(Product).


% Task 6.7 --- At first I thought that lists couldn’t be used, so I came up with a dynamic predicate :(

% read_n_m(-N:integer, -M:integer)
% Reads values for N and M from the user.
read_n_m(N, M) :-
    write('Enter n: '), read(N), nl,
    write('Enter m: '), read(M).

% Declare term_seen/1 as a dynamic predicate.
:- dynamic term_seen/1.

% distinct_powers(+N:integer, +M:integer, -Count:integer)
% Counts the number of distinct terms of the form A^B where A is in the range [2, N] and B is in the range [2, M].
distinct_powers(N, M, Count) :-
    retractall(term_seen(_)),
    distinct_powers(2, N, 2, M, 0, Count).

% distinct_powers(+A:integer, +N:integer, +B:integer, +M:integer, +Acc:integer, -Count:integer)
% Helper predicate for distinct_powers/3.
% Base case: If A > N, the count is the accumulated value Acc.
distinct_powers(A, N, _, _, Acc, Count) :-
    A > N,
    Count is Acc.

% Recursive case: If A =< N, process the current term A^B.
distinct_powers(A, N, B, M, Acc, Count) :-
    A =< N,
    (   B > M
    ->
        NextA is A + 1,
        distinct_powers(NextA, N, 2, M, Acc, Count)
    ;
        P is A ** B,
        (   term_hash(P, H),
            \+ term_seen(H)
        ->  % If the term has not been seen before, assert it and increment the count.
            assertz(term_seen(H)),
            NextAcc is Acc + 1
        ;
            NextAcc is Acc
        ),
        NextB is B + 1,
        distinct_powers(A, N, NextB, M, NextAcc, Count)
    ).

% write_result_task6(+Count:integer)
% Writes the result of task6.
write_result_task6(Count) :-
    write('The result of task6 is: '),
    write(Count), nl.

% task6
% Main predicate for task6.
% Reads N and M, counts the distinct terms, and writes the result.
task6 :-
    read_n_m(N, M),
    distinct_powers(N, M, Count),
    write_result_task6(Count).
