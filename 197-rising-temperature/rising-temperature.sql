select w.id
from weather w
join weather we
on datediff(w.recordDate, we.recordDate) = 1
where w.temperature > we.temperature;