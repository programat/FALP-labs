% akinator-computer-problems.pl

main :-
    retractall(asked(_,_)),
    fault(Problem),
    !,
    nl,
    write('The problem is '), write(Problem), write(.), nl.
main :-
    nl,
    write('The problem cannot be recognized.'), nl.

problem(disc_format) :-
    query('Does the computer show error cannot format').

problem(boot_failure) :-
    query('Does the computer show boot failure').

problem(bad_sector) :-
    query('Does the computer show bad sector error').

problem(cannot_read) :-
    query('Does the computer show cannot read from specified device').

problem(long_beep) :-
    query('Is there a long beep during bootup').

problem(short_beep) :-
    query('Is there a short beep during bootup').

problem(two_long_beeps) :-
    query('Are there two long beeps during bootup').

problem(two_short_beeps) :-
    query('Are there two short beeps during bootup').

problem(blank_display) :-
    query('Is there a blank display during bootup').

problem(repeating_short_beeps) :-
    query('Are there repeating short beeps during bootup').

problem(continuous_beeps) :-
    query('Is there a continuous beep during bootup').

problem(no_beep) :-
    query('Is there a beep during bootup').

problem(not_printing) :-
    query('Is there a problem with printing').

problem(missing_dots) :-
    query('Is there a missing character during printing').

problem(non_uniform_printing) :-
    query('Is there uniform printing').

problem(spread_ink) :-
    query('Is there spreading of ink during printing').

problem(paper_jam) :-
    query('Is there a paper jam during printing').

problem(out_of_paper) :-
    query('Is there out-of- paper error during printing').

% New problems
problem(computer_does_not_turn_on) :-
    query('Is the computer not turning on at all, showing no signs of power?').

problem(computer_turns_on_but_no_display) :-
    query('Is the computer turning on but not displaying anything on the screen?').

problem(overheating) :-
    query('Is the computer unusually hot?').

problem(fan_noise) :-
    query('Is there loud or unusual fan noise?').

problem(graphical_artifacts) :-
    query('Is the computer displaying artifacts or unusual graphical glitches?').

problem(screen_freezes) :-
    query('Are there random screen freezes or blackouts?').

problem(driver_gpu_errors) :-
    query('Do you experience driver crashes or errors while using graphics-intensive applications?').

% New faults recognitions without additional questions

fault(power_supply_issue) :-
    problem(computer_does_not_turn_on).

fault(display_issue) :-
    problem(computer_turns_on_but_no_display).

% New faults recognitions with additional questions

fault(cooling_issue) :-
    problem(overheating),
    problem(fan_noise).

fault(graphics_card_issue) :-
    problem(graphical_artifacts),
    problem(screen_freezes),
    problem(driver_errors).

fault(cpu_failure) :-
    problem(overheating), % the new problem
    problem(no_beep),
    problem(boot_failure).

% ---

fault(power_supply) :-
    problem(repeating_short_beeps),
    problem(continuous_beeps),
    problem(blank_display),
    problem(no_beep).

fault(display_adapter) :-
    problem(long_beep),
    problem(two_short_beeps),
    problem(blank_display),
    problem(no_beep).

fault(motherboard) :-
    problem(long_beep),
    problem(short_beep).

fault(hard_disc) :-
    problem(two_short_beeps),
    problem(blank_display).

fault(booting_problem) :-
    problem(bad_sector),
    problem(boot_failure).

fault(floppy_disk_unusable) :-
    problem(bad_sector),
    problem(cannot_read),
    problem(disc_format).

fault(printer_head) :-
    problem(not_printing),
    problem(missing_dots),
    problem(nonuniform_printing).

fault(ribbon) :-
    problem(not_printing),
    problem(missing_dots),
    problem(spread_ink).

fault(paper) :-
    problem(not_printing),
    problem(paper_jam),
    problem(out_of_paper).

query(Prompt) :-
    (   asked(Prompt, Reply) -> true
    ;   nl, write(Prompt), write(' (y/n)? '),
        read(X),(X = y -> Reply = y ; Reply = n),
	assert(asked(Prompt, Reply))
    ),
    Reply = y.
