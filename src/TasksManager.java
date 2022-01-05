import java.util.HashMap;

public class TasksManager {

     static HashMap<Integer, Task> tasksManager;
     static HashMap<Integer, SubTask> subTasksManager;
     static HashMap<Integer, Epic> epicsManager;


     public static void main(String[] args) {

          tasksManager = new HashMap<>();
          subTasksManager = new HashMap<>();
          epicsManager = new HashMap<>();

     }

     // 2.1 Получение списка всех задач.
     public static String getTasks () {
          String result = null;
          if (!tasksManager.isEmpty()) {
               for (Task task : tasksManager.values()) {
                    result = task.toString();
               }
          }
          return result;
     }

     // 2.2 Получение списка всех эпиков.
     public static String getEpics () {
          String result = null;
          if (!epicsManager.isEmpty()) {
               for (Epic epic : epicsManager.values()) {
                    result = epic.toString();
               }
          }
          return result;
     }

     // 2.3 Получение списка всех подзадач определённого эпика.
     public static String getSubtasksFromEpicId (Epic epic) {
          String result = null;
          if (!epicsManager.isEmpty() && epicsManager.containsValue(epic)) {
               if (!epicsManager.get(epic.id).subTasks.isEmpty()) {
                    for (SubTask subtask : epicsManager.get(epic.id).subTasks.values()) {
                         result = subtask.toString();
                    }
               }
          }
          return result;
     }

     // 2.4.1 Получение задачи по идентификатору.
     public static String getTaskFromId (Integer taskId) {
          String result = null;
          if (!tasksManager.isEmpty()) {
               for (Task task : tasksManager.values()) {
                    if (task.id == taskId) {
                         result = task.toString();
                    }
               }
          }
          return result;
     }

     // 2.4.2 Получение эпика по идентификатору.
     public static String getEpicFromId (Integer epicId) {
          String result = null;
          if (!epicsManager.isEmpty()) {
               for (Epic epic : epicsManager.values()) {
                    if (epic.id == epicId) {
                         result = epic.toString();
                    }
               }
          }
          return result;
     }

     // 2.4.3 Получение подзадачи по идентификатору.
     public static String getSubtaskFromId (Integer subTaskId) {
          String result = null;
          if (!epicsManager.isEmpty()) {
               for (Epic epic : epicsManager.values())
                    for (SubTask subtask : epic.subTasks.values()) {
                         if (subtask.id == subTaskId) {
                              result = subtask.toString();
                         }
                    }
          }
          return result;
     }

     // 2.5.1 Добавить новую задачу.
     public static void addTask (Task task) {
          tasksManager.put(task.id, task);
     }

     // 2.5.2 Добавить новый эпик и подзадачу.
     public static void addEpicAndSubtask (Epic epic, SubTask subtask) {
          epicsManager.put(epic.id, epic);
          subTasksManager.put(subtask.id, subtask);
          epic.subTasks.put(subtask.id, subtask);
          subtask.epics.put(epic.id, epic);
          changeStatusEpic();
     }

     // 2.6.1 Обновление задачи по идентификатору.
     public static void updateTaskFromId (Task task) {
          if (tasksManager.containsValue(task)) {
               tasksManager.put(task.id, task);
          }
     }

     // 2.6.2 Обновление эпика по идентификатору.
     public static void updateEpicFromId (Epic epic) {
          if (epicsManager.containsValue(epic)) {
               epicsManager.put(epic.id, epic);
          }
          changeStatusEpic();
     }

     // 2.6.3 Обновление подзадачи по идентификатору.
     public static void updateSubTask(SubTask subtask) {
          if (subTasksManager.containsKey(subtask.id)) {
               subTasksManager.put(subtask.id, subtask);
          }
          if (!epicsManager.isEmpty()) {
              for (Epic epic : epicsManager.values()) {
                   if (!epic.subTasks.isEmpty()) {
                        for (SubTask subTask : epic.subTasks.values()) {
                             if (subTask.id == subtask.id) {
                                  epic.subTasks.put(subtask.id, subtask);
                             }
                        }
                   }
              }
          }
          changeStatusEpic();
     }



     // 2.7.1 Удаление всего по идентификатору.
     public static void removeAllTasksSubTasksEpics() {
          tasksManager.clear();
          subTasksManager.clear();
          epicsManager.clear();
     }

     // 2.7.2 Удаление задачи по идентификатору.
     public static void removeAllTasks (Integer taskId) {
          if (!tasksManager.isEmpty()) {
               for (Task task : tasksManager.values()) {
                    if (task.id == taskId) {
                         tasksManager.remove(task.id);
                    }
               }
          }
     }

     // 2.7.3 Удаление эпика по идентификатору.
     public static void removeAllEpics (Integer epicId) {
          if (!epicsManager.isEmpty()) {
               for (Epic epic : epicsManager.values()) {
                    if (epic.id == epicId) {
                         epicsManager.remove(epic.id);
                    }
               }
          }
          if (!subTasksManager.isEmpty()) {
               for (SubTask subtask : subTasksManager.values()) {
                    for (Epic epic : subtask.epics.values()) {
                         if (epic.id == epicId) {
                              subtask.epics.remove(epic.id, epic);
                         }
                    }
               }
          }
     }

     // 2.7.4 Удаление подзадачи по идентификатору.
     public static void removeAllSubTasks (Integer subTaskId) {
          if (!epicsManager.isEmpty()) {
               for (Epic epic : epicsManager.values())
                    for (SubTask subtask : epic.subTasks.values()) {
                         if (subtask.id == subTaskId) {
                              epic.subTasks.remove(subtask.id, subtask);
                         }
                    }
          }
          if (!subTasksManager.isEmpty()) {
               for (SubTask subtask : subTasksManager.values()) {
                    if (subtask.id == subTaskId) {
                         subTasksManager.remove(subtask.id);
                    }
               }
          }
          changeStatusEpic();
     }


     // ** Изменить статус эпика в зависимости статуса подзадачи.
     public static void changeStatusEpic () {
          if (!epicsManager.isEmpty()) {
               for (Epic epic : epicsManager.values())
                    if (epic.subTasks.values().isEmpty()) {
                         epic.status = "IN_PROGRESS";
                    }
          }
          if (!epicsManager.isEmpty()) {
               for (Epic epic : epicsManager.values()) {
                    boolean flag = false;
                    boolean flag_1 = true;
                    for (SubTask subtask : epic.subTasks.values()) {
                         if (subtask.status.equals("NEW")) {
                              flag = true;
                         } else {
                              flag_1 = false;
                         }
                         if (flag && flag_1) {
                              epic.status = "NEW";
                         } else {
                              epic.status = "IN_PROGRESS";
                         }
                    }
               }
          }
          if (!epicsManager.isEmpty()) {
               for (Epic epic : epicsManager.values()) {
                    boolean flag = false;
                    boolean flag_1 = true;
                    for (SubTask subtask : epic.subTasks.values()) {
                         if (subtask.status.equals("DONE")) {
                              flag = true;
                         } else {
                              flag_1 = false;
                         }
                    }
                    if (flag && flag_1) {
                         epic.status = "DONE";
                    } else {
                         epic.status = "IN_PROGRESS";
                    }
               }
          }
     }

}
