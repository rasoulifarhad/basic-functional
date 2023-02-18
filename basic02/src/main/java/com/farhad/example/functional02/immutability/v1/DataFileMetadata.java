package com.farhad.example.functional02.immutability.v1;

import java.io.File;
import java.io.IOException;
import  lombok.With;
import java.nio.file.Files;

import com.farhad.example.functional02.common.DataFileException;

import cyclops.control.Eval;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * With immutable Objects the key insight is that we need to do the oppostite. We need to make all of our fields 
 * values available prior to declaration and initialization.
 * 
 * A good technique to help you build confidence is to declare all field values as local (ideally final) values 
 * first.
 * 
 * Note: 
 *      An expression evaluates to a value. A statement does something. Statements represent an action or command 
 *      e.g print statements, assignment statements. Expression is a combination of variables, operations and values 
 *      that yields a result value.
 * 
 * * Expressions
 * 
 * When fields and values are declared final it is much cleaner to declare and initialize them with an expression 
 * that evaluates to the required value. 
 * 
 * The code below uses a statement to initialize a pre-declared immutable field.
 * 
 *    final int i;
 *    if(isTrue)
 *      i=1;
 *    else
 *      i=2;
 * 
 * It’s much cleaner to use an expression (a method or function with a return type) to initialize i
 * 
 *    final int i = oneOrElseTwo( isTrue );
 * 
 * An alternative method of creating an expression is to inline the logic with the ternary operator
 * 
 *    final int i = isTrue ? 1 : 2;
 * 
 * * For Immutable Types : Make field data available before Object creation
 * 
 *    Move the variable declarations up! (That’s up as in up towards the top of your monitor, well above the new keyword!)
 * 
 */
@Getter
@AllArgsConstructor
public class DataFileMetadata {
    
    private final long customerId ;
    private final String type ;
    @With
    private final File file ;
    
    private final Eval<String> contents = Eval.later( this::loadContents );

    public String getContents() {
        return contents.get();
    }
    private String loadContents() {

        try {
            return new String(Files.readAllBytes(file.toPath()));
        } catch (IOException ex) {
           throw new DataFileException(ex)    ;   
        }
    }
}
