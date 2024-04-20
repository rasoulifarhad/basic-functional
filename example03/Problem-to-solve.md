### Problem

[from](http://debasishg.blogspot.com/2013/03/an-exercise-in-refactoring-playing.html)
[and](https://dzone.com/articles/refactoring-exercise-using)

**A Problem of Transformation ..**

The problem is to compute the salary of a person through composition of multiple salary components calculated based on some percentage of other components. It's a problem of applying repeated transformations to a pipeline of successive computations - hence it can be generalized as a case study in function composition. But with some constraints as we will see shortly.

Let's say that the salary of a person is computed as per the following algorithm :

- basic = the basic component of his salary
- allowances = 20% of basic
- bonus = 10% of (basic + allowances)
- tax = 30% of (basic + allowances + bonus)
- surcharge = 10% of (basic + allowances + bonus - tax)

Note that the computation process starts with a basic salary, computes successive components taking the input from the previous computation of the pipeline. But there's a catch though, which makes the problem a bit more interesting from the modleing perspective. Not all components of the salary are mandatory - of course the `basic` is mandatory. Hence the final components of the salary will be determined by a configuration object which can be like the following ..

```scala

// an item = true means the component should be activated in the computation
case class SalaryConfig(
  surcharge: Boolean    = true, 
  tax: Boolean          = true, 
  bonus: Boolean        = true,
  allowance: Boolean    = true 
)

```

So when we compute the salary we need to take care of this configuration object and activate the relevant components for calculation.

**A Function defines a Transformation ..**

Let's first translate the above components into separate Scala functions ..

```scala

// B = basic + 20%
val plusAllowance = (b: Double) => b * 1.2

// C = B + 10%
val plusBonus = (b: Double) => b * 1.1

// D = C - 30%
val plusTax = (b: Double) => 0.7 * b

// E = D - 10%
val plusSurcharge = (b: Double) => 0.9 * b

```

But we need to selectively activate and deactivate the components depending on the SalaryConfig passed. Here's the version that comes straight from the imperative mindset ..

** The Imperative Solution ..**

```scala

// no abstraction, imperative, using var
def computeSalary(sc: SalaryConfig, basic: Double) = {
  var salary = basic
  if (sc.allowance) salary = plusAllowance(salary)
  if (sc.bonus) salary = plusBonus(salary)
  if (sc.tax) salary = plusTax(salary)
  if (sc.surcharge) salary = plusSurcharge(salary)
  salary
}

```

** Thinking in terms of Expressions and Composition ..**

Think in terms of expressions (not statements) that compose. We have functions defined above that we could compose together and get the result. But, but .. the config, which we somehow need to incorporate as part of our composable expressions.

So direct composition of functions won't work because we need some conditional support to take care of the config. How else can we have a chain of functions to compose ?

Note that all of the above functions for computing the components are of type (Double => Double). Hmm .. this means they are endomorphisms, which are functions that have the same argument and return type - "endo" means "inside" and "morphism" means "transformation". So an endomorphism maps a type on to itself. 

Scalaz defines it as ..


