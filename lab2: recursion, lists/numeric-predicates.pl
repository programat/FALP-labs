% numeric-predicates.pl

% Task 1 ---

% max(+X:number, +Y:number, +U:number, -Z:number)
% True if Z is the atom representing the maximum of X, Y, and U.
max(X, Y, U, Z) :-
    U >= X,
    U >= Y,
    Z = U.


% fact_up(+N:integer, -X:integer)
% Calculates the factorial of N using tail recursion.
% Base case: factorial of 0 is 1.
fact_up(0, 1).

% Recursive case: factorial of N > 0 is N * factorial(N-1).
fact_up(N, X) :-
    N > 0,
    N1 is N - 1,
    fact_up(N1, X1),
    X is N * X1.

% fact_down(+N:integer, +Acc:integer, -X:integer)
% Calculates the factorial of N using accumulator-based recursion.
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
% Calculates the sum of digits of N using tail recursion.
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
% Calculates the sum of digits of N using accumulator-based recursion.
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
