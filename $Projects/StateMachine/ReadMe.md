<h1>State Machine</h1>
<p> Requires a binary path input such as</p>
<pre>
  (
 (
  (0, 1),
  (q0, q1, q2),
  q0,
  (q2),
  ((q0, 0, q0), (q0, 1, q0),(q0, 0, q1),(q1, 1, q2)))
 ), 
 (1101, 0001, 1110)
)
</pre>

<p>Then outputs true or false based on if the input can traverse the state machine </p>
