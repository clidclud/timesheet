package ru.gb.timesheet;

public class REST {

    /*
     * HTTP - протокол
     * gRPC - протокол
     * WebSockets - протокол
     *
     *  Путь\эндпоинт\ручка\ресурс - /timesheet
     *
     * REST - набор соглашений, как оформлять / проектировать ресурсы вашего сервиса
     *
     * 1. Не использовать глаголы в путях
     * /deleteTimesheet/{id} - плохо
     * DELETE /timesheet/{id} - хорошо
     *
     * 2. Ресурсы должны вкладываться друг в друга
     * GET /users/{userId}/roles/{roleId} - получить роль с идентификатором roleId у юзеров userId
     * GET /roles/{id}
     * GET /departments/{id}/employees/{id}/head/consultant/roles/{id}
     *
     * Получить юзеров, у которых имя содержит какую-то подстроку
     * GET /users?name-like="asd" -> 204 No Content
     * Get /users?sort-By=age&sort-order=desc
     *
     * 3. Рекомендация: использовать множественное число для ресурсов
     * Вместо /user использовать /users
     *
     * 4. Слова внутри ресурса соединяются дефисом
     * GET github.com/project/pull-requests/{id}
     *
     */
}
