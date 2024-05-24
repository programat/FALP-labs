% comb-objects-predicates.pl

% generate_arrangements_with_repetition(+K:integer, +Alphabet:list, -Arrangement:list)
% Generates arrangements with repetition of length K from the given Alphabet.
generate_arrangements_with_repetition(0, _, []).
generate_arrangements_with_repetition(K, Alphabet, [X|T]) :-
   K > 0,
   member(X, Alphabet),
   K1 is K - 1,
   generate_arrangements_with_repetition(K1, Alphabet, T).

% print_list(+List:list)
% Prints the elements of the list.
print_list([]).
print_list([X|T]) :- write(X), write(' '), print_list(T).

% arrangements_with_repetition(+K:integer, +Alphabet:list)
% Generates and prints all arrangements with repetition of length K from the given Alphabet.
arrangements_with_repetition(K, Alphabet) :-
   arrangements_with_repetition(K, Alphabet, Res),
   print_list(Res), nl, fail.

% generate_combinations_without_repetition(+K:integer, +Alphabet:list, -Combination:list)
% Generates combinations without repetition of length K from the given Alphabet.
generate_combinations_without_repetition(0, _, []) :- !.
generate_combinations_without_repetition(K, [El|RestAlph], [El|Comb]) :-
    Knew is K - 1,
    generate_combinations_without_repetition(Knew, RestAlph, Comb).
generate_combinations_without_repetition(K, [_|RestAlph], Comb) :-
    generate_combinations_without_repetition(K, RestAlph, Comb).

% combinations_without_repetition(+K:integer, +Alphabet:list)
% Generates and prints all combinations without repetition of length K from the given Alphabet.
combinations_without_repetition(K, Alphabet) :-
    generate_combinations_without_repetition(K, Alphabet, Res),
    print_list(Res), nl, fail.

% generate_arrangements_with_repetition_nonrec(+K:integer, +Alphabet:list, -Arrangement:list)
% Generates arrangements with repetition of length K from the given Alphabet using a non-recursive approach.
generate_arrangements_with_repetition_nonrec(K, Alphabet, A) :-
   length(A, K),
   maplist(member_of(Alphabet), A).

% arrangements_with_repetition_nonrec(+K:integer, +Alphabet:list)
% Generates and prints all arrangements with repetition of length K from the given Alphabet using a non-recursive approach.
arrangements_with_repetition_nonrec(K, Alphabet) :-
    generate_arrangements_with_repetition(K, Alphabet, A),
    print_list(A), nl, fail.

% member_of(+List:list, -Element:term)
% True if Element is a member of List.
member_of(L, X) :- member(X, L).

% combinations_without_repetition_nonrec(+K:integer, +List:list, -Combinations:list)
% Generates all combinations without repetition of length K from the given List using a non-recursive approach.
combinations_without_repetition_nonrec(K, L, Combinations) :-
    findall(C, (my_subset(L, C), length(C, K)), Combinations).

% my_subset(+List:list, -Subset:list)
% Generates all subsets of the given List.
my_subset([], []).
my_subset([E|Tail], [E|NTail]):-
    my_subset(Tail, NTail).
my_subset([_|Tail], NTail):-
    my_subset(Tail, NTail).

% words_with_3_a(+K:integer, +Alphabet:list, -Word:list)
% Generates all words of length K containing exactly 3 occurrences of the letter 'a' from the given Alphabet.
words_with_3_a(K, L, W) :-
   length(W, K),
   append(_, ['a'|T1], W),
   append(_, ['a'|T2], T1),
   append(_, ['a'|_], T2),
   subtract(W, ['a','a','a'], R),
   maplist(member_of(L), R).

% make_pos_list(+K:integer, +CurPos:integer, -PosList:list)
% Generates a list of positions from CurPos to K.
make_pos_list(K, K, []) :- !.
make_pos_list(K, CurPos, [NewPos|TailPos]) :- NewPos is CurPos + 1, make_pos_list(K, NewPos, TailPos).

% make_3a_empty_word(+K:integer, +CurIndex:integer, +PosList:list, -WordEmpty3a:list)
% Generates an empty word of length K with 3 occurrences of 'a' at the positions specified in PosList.
make_3a_empty_word(K, K, _, []) :- !.
make_3a_empty_word(K, CurIndex, [NewIndex|PosTail], [a|Tail]) :-
    NewIndex is CurIndex + 1, make_3a_empty_word(K, NewIndex, PosTail, Tail), !.
make_3a_empty_word(K, CurIndex, PosList, [_|Tail]) :-
    NewIndex is CurIndex + 1, make_3a_empty_word(K, NewIndex, PosList, Tail).

% build_word(+Word:list, +WordEmpty3a:list, +RestWord:list)
% Builds a word by filling the empty positions in WordEmpty3a with letters from RestWord.
build_word([], [], _) :- !.
build_word([a|WordTail], [X|WordEmpty3aTail], RestWord) :-
    nonvar(X), build_word(WordTail, WordEmpty3aTail, RestWord), !.
build_word([Y|WordTail], [X|WordEmpty3aTail], [Y|RestWordTail]) :-
    var(X), build_word(WordTail, WordEmpty3aTail, RestWordTail).

% build_3a_words_of_k(+Alphabet:list, +K:integer, -Word:list)
% Generates all words of length K containing exactly 3 occurrences of 'a' from the given Alphabet.
build_3a_words_of_k(Alphabet, K, Word) :-
    make_pos_list(K, 0, PosList),
    generate_combinations_without_repetition(3, PosList, Pos_a_List),
    make_3a_empty_word(K, 0, Pos_a_List, WordEmpty3a),
    Alphabet = [a|NewAlphabet],
    M is K - 3,
    generate_arrangements_with_repetition(M, NewAlphabet, RestWord),
    build_word(Word, WordEmpty3a, RestWord).


% task 4.1 --- arrangements without repetition

% arrangement(+Alphabet:list, -Arrangement:list)
% Generates an arrangement of the elements in Alphabet without repetition.
arrangement([], []).
arrangement(Alphabet, [X|T]) :- select(X, Alphabet, R), arrangement(R, T).

% write_arrangements(+File:atom, +List:list)
% Writes all arrangements of List to the specified File.
write_arrangements(File, List) :-
    open(File, write, Stream),
    write_arrangements(Stream, List, List),
    close(Stream).

% write_arrangements(+Stream:stream, +List:list, +OriginalList:list)
% Helper predicate to write arrangements to the Stream.
write_arrangements(_, [], _).
write_arrangements(Stream, _, OriginalList) :-
    arrangement(OriginalList, Arrangement),
    format(Stream, "~w~n", [Arrangement]),
    fail.
write_arrangements(_, _, _).

% task 4.2 --- permutations

% permute(+Alphabet:list, -Permutation:list)
% Generates a permutation of the elements in Alphabet.
permute([], []).
permute([X|T], List) :- permute(T, ListT), select(X, List, ListT).

% write_permutations(+File:atom, +List:list)
% Writes all permutations of List to the specified File.
write_permutations(File, List) :-
    open(File, write, Stream),
    write_permutations(Stream, List, List),
    close(Stream).

% write_permutations(+Stream:stream, +List:list, +OriginalList:list)
% Helper predicate to write permutations to the Stream.
write_permutations(_, [], _).
write_permutations(Stream, _, OriginalList) :-
    permute(OriginalList, Permutation),
    format(Stream, "~w~n", [Permutation]),
    fail.
write_permutations(_, _, _).

% write_permut_and_arrang
% Writes all arrangements and permutations of the alphabet [a, b, c, d] to files.
write_permut_and_arrang :-
    Alphabet = [a, b, c, d],
    open('data/arrang.txt', write, Stream1),
    findall(A, arrangement(Alphabet, A), Arrangements),
    maplist({Stream1}/[X]>>format(Stream1, "~w~n", [X]), Arrangements),
    close(Stream1),
    open('data/permute.txt', write, Stream2),
    findall(P, permute(Alphabet, P), Permutations),
    maplist({Stream2}/[X]>>format(Stream2, "~w~n", [X]), Permutations),
    close(Stream2).

% task 4.6 --- words of length K (5) with 2 'a', the rest not repeated

% words_with_2_a_5(+Alphabet:list, -Word:list)
% Generates words of length 5 with exactly 2 'a's and the rest unique letters from Alphabet.
words_with_2_a_5(Alphabet, Word) :-
    length(Word, 5),
    % Insert two 'a's into the word
    select(a, Word, Rest1),
    select(a, Rest1, Rest2),
    % The remaining three letters must be unique and not 'a'
    Rest2 = [B, C, D],
    member(B, Alphabet),
    member(C, Alphabet),
    member(D, Alphabet),
    B \= a, C \= a, D \= a,
    B \= C, B \= D, C \= D.

% task 4.8 --- words of length K (6) with 2 letters repeated twice, the rest not repeated

% words_with_2_repeated_6(+Alphabet:list, -Word:list)
% Generates words of length 6 with exactly 2 letters repeated twice and the rest unique letters from Alphabet.
words_with_2_repeated_6(Alphabet, Word) :-
    length(Word, 6),
    % Select two letters to be repeated twice
    member(A, Alphabet),
    member(B, Alphabet),
    A \= B,
    % The remaining two letters must be unique and not 'A' or 'B'
    member(C, Alphabet),
    member(D, Alphabet),
    C \= D, C \= A, C \= B, D \= A, D \= B,
    % Form the word and check its uniqueness
    permutation([A, A, B, B, C, D], Word).

% write_words_to_file(+Predicate:atom, +Alphabet:list, +Filename:atom)
% Writes all words generated by Predicate from Alphabet to the specified Filename.
write_words_to_file(Predicate, Alphabet, Filename) :-
    open(Filename, write, Stream),
    forall(call(Predicate, Alphabet, Word), (write(Stream, Word), nl(Stream))),
    close(Stream).

% write_all_words_to_files
% Writes all words of length 5 with 2 'a's and words of length 6 with 2 letters repeated twice to files.
write_all_words_to_files :-
    Alphabet = [a, b, c, d, e, f, g, h, i, j],
    write_words_to_file(words_with_2_a_5, Alphabet, 'data/words_5.txt'),
    write_words_to_file(words_with_2_repeated_6, Alphabet, 'data/words_6.txt').

