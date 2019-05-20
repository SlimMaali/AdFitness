<?php

namespace AppBundle\Entity;

/**
 * trainingSession
 */
class trainingSession
{
    /**
     * @var int
     */
    private $id;

    /**
     * @var int
     */
    private $idUser;

    /**
     * @var int
     */
    private $idSession;

    /**
     * @var \DateTime
     */
    private $date;

    /**
     * @var int
     */
    private $duration;

    /**
     * @var int
     */
    private $steps;


    /**
     * Get id
     *
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * Set idUser
     *
     * @param integer $idUser
     *
     * @return trainingSession
     */
    public function setIdUser($idUser)
    {
        $this->idUser = $idUser;

        return $this;
    }

    /**
     * Get idUser
     *
     * @return int
     */
    public function getIdUser()
    {
        return $this->idUser;
    }

    /**
     * Set idSession
     *
     * @param integer $idSession
     *
     * @return trainingSession
     */
    public function setIdSession($idSession)
    {
        $this->idSession = $idSession;

        return $this;
    }

    /**
     * Get idSession
     *
     * @return int
     */
    public function getIdSession()
    {
        return $this->idSession;
    }

    /**
     * Set date
     *
     * @param \DateTime $date
     *
     * @return trainingSession
     */
    public function setDate($date)
    {
        $this->date = $date;

        return $this;
    }

    /**
     * Get date
     *
     * @return \DateTime
     */
    public function getDate()
    {
        return $this->date;
    }

    /**
     * Set duration
     *
     * @param integer $duration
     *
     * @return trainingSession
     */
    public function setDuration($duration)
    {
        $this->duration = $duration;

        return $this;
    }

    /**
     * Get duration
     *
     * @return int
     */
    public function getDuration()
    {
        return $this->duration;
    }

    /**
     * Set steps
     *
     * @param integer $steps
     *
     * @return trainingSession
     */
    public function setSteps($steps)
    {
        $this->steps = $steps;

        return $this;
    }

    /**
     * Get steps
     *
     * @return int
     */
    public function getSteps()
    {
        return $this->steps;
    }
}

